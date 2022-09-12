package example.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import example.config.jwt.CustomUserDetails;
import example.entity.OrderEntity;
import example.entity.OrderItemByTicketEntity;
import example.entity.OrderItemEntity;
import example.entity.UserEntity;
import example.payload.request.CartRequest;
import example.payload.request.InforRequest;
import example.payload.request.OrderRequest;
import example.payload.response.OrderItemResponse;
import example.payload.response.OrderObjectResponse;
import example.payload.response.OrderResponse;
import example.payload.response.TicketResponse;
import example.repository.CartItemByTicketRepository;
import example.repository.CartItemRepository;
import example.repository.CartRepository;
import example.repository.OrderItemByTicketRepository;
import example.repository.OrderItemRepository;
import example.repository.OrderRepository;
import example.repository.ServiceRepository;
import example.repository.TicketRepository;
import example.repository.UserRepository;
import example.service.IOrderService;

@Service
public class OrderService implements IOrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	OrderItemByTicketRepository orderItemByTicketRepository;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	CartItemRepository cartItemRepository;
	
	@Autowired
	CartItemByTicketRepository cartItemByTicketRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ServiceRepository serviceRepository;
	
	@Autowired
	TicketRepository ticketRepository;

	@Override
	public Optional<OrderResponse> createOrder(OrderRequest request) {
		
		// Authentication
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		UserEntity userEntity = userRepository.findOneByUsername(userDetails.getUsername());
		
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setCreateDate(new Date());
		orderEntity.setModifiedDate(new Date());
		orderEntity.setEmail(request.getInfor().getEmail());
		orderEntity.setFullname(request.getInfor().getFullname());
		orderEntity.setPhone(request.getInfor().getPhone());
		orderEntity.setUserOrder(userEntity);
		orderEntity.setTotal(totalOrderPrice(request.getItems()));
		orderEntity.setOrderStatus(true);
		
		OrderEntity rsOrder = orderRepository.save(orderEntity);
		
		if (rsOrder != null) {
			
			List<OrderItemEntity> listOrderItem = new ArrayList<>();
			for (int i = 0; i < request.getItems().size(); i++) {
				OrderItemEntity orderItem = new OrderItemEntity();
				orderItem.setCreateDate(new Date());
				orderItem.setModifiedDate(new Date());
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");  
				try {
					orderItem.setBookingDate(formatter.parse(request.getItems().get(i).getBookingDate()));
				} catch (ParseException e) {
					throw new NullPointerException();
				}
				orderItem.setNote(request.getItems().get(i).getNote());
				orderItem.setOrderOrderItem(rsOrder);
				orderItem.setTotal(totalOrderItemPrice(request.getItems().get(i).getTickets()));
				orderItem.setServiceOrderItem(serviceRepository.findOneById(request.getItems().get(i).getIdService()));
				OrderItemEntity rsOrderItem = orderItemRepository.save(orderItem);
				listOrderItem.add(rsOrderItem);
				
				if (rsOrderItem != null) {
					for (TicketResponse ticket : request.getItems().get(i).getTickets()) {
						OrderItemByTicketEntity orderItemByTicketEntity = new OrderItemByTicketEntity();
						orderItemByTicketEntity.setAmount(ticket.getAmountTicket());
						orderItemByTicketEntity.setCurrentPrice(ticket.getValueTicket());
						orderItemByTicketEntity.setType(ticket.getTypeTicket());
						orderItemByTicketEntity.setOrderItemBy(rsOrderItem);
						orderItemByTicketEntity.setTicketBy(ticketRepository.findOneById(ticket.getIdTicket()));
						orderItemByTicketRepository.save(orderItemByTicketEntity);
					}
				}
				
				if (!request.isStatusOrder()) {
					// Mua thong qua gio hang
					 cartItemByTicketRepository.deleteAll(cartItemByTicketRepository.findAllByCartItemById(request.getItems().get(i).getIdCartItem()));
					 cartItemRepository.deleteById(request.getItems().get(i).getIdCartItem());
				}
			}
			
			return Optional.ofNullable(new OrderResponse(rsOrder.getId(), convertToInforRequest(rsOrder.getId()), convertToListCartRequest(listOrderItem)));
		} throw new NullPointerException();
	}
	
	
	
	public int totalOrderPrice(List<CartRequest> items) {
		int total = 0;
		for (CartRequest item : items) {
			total = total + totalOrderItemPrice(item.getTickets());
		}
		return total;
	}
	
	public int totalOrderItemPrice(List<TicketResponse> tickets) {
		int total = 0;
		for (TicketResponse ticket : tickets) {
			total = total + ticket.getAmountTicket()*ticket.getValueTicket();
		}
		return total;
	}


	public InforRequest convertToInforRequest(Long idOrder) {
		OrderEntity orderEntity = orderRepository.findOneById(idOrder);
		InforRequest infor = new InforRequest();
		infor.setEmail(orderEntity.getEmail());
		infor.setPhone(orderEntity.getPhone());
		infor.setFullname(orderEntity.getFullname());
		return infor;
	}
	
	public List<CartRequest> convertToListCartRequest(List<OrderItemEntity> listOrderItem) {
		List<CartRequest> listCartRequest = new ArrayList<>();
		for (int i = 0; i < listOrderItem.size(); i++) {
			CartRequest cartRequest = new CartRequest();
			cartRequest.setBookingDate(listOrderItem.get(i).getBookingDate().toString());
			cartRequest.setIdService(listOrderItem.get(i).getServiceOrderItem().getId());
			cartRequest.setNote(listOrderItem.get(i).getNote());
			List<OrderItemByTicketEntity> orderItemByTickets = orderItemByTicketRepository.findAllByOrderItemById(listOrderItem.get(i).getId());
			List<TicketResponse> listTicket = new ArrayList<>();
			for (OrderItemByTicketEntity orderItemTicket : orderItemByTickets) {
				TicketResponse ticket = new TicketResponse();
				ticket.setIdTicket(orderItemTicket.getTicketBy().getId());
				ticket.setAmountTicket(orderItemTicket.getAmount());
				ticket.setNote(ticketRepository.findOneById(orderItemTicket.getTicketBy().getId()).getNote());
				ticket.setTypeTicket(orderItemTicket.getType());
				ticket.setValueTicket(orderItemTicket.getCurrentPrice());
				listTicket.add(ticket);
			}
			cartRequest.setTickets(listTicket);
			listCartRequest.add(cartRequest);
		}
		
		return listCartRequest;
	}



	@Override
	public List<OrderObjectResponse> findAll(org.springframework.data.domain.Pageable pageable) {
		
		// Authentication
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		UserEntity userEntity = userRepository.findOneByUsername(userDetails.getUsername());
		
		List<OrderObjectResponse> listOrder = new ArrayList<>();
		List<OrderEntity> orders = orderRepository.findByUserOrderId(userEntity.getId(), pageable).getContent();
		
		for (OrderEntity order : orders) {
			listOrder.add(convertToOrderObjectResponse(order));
		}
		return listOrder;
	}
	
	
	public int totalItem() {
		// Authentication
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		UserEntity userEntity = userRepository.findOneByUsername(userDetails.getUsername());
		
		return (int) orderRepository.findAllByUserOrderId(userEntity.getId()).size();
	}



	@Override
	public OrderObjectResponse findOne(Long idOrder) {
		OrderEntity orderEntity = orderRepository.findOneById(idOrder);
		return convertToOrderObjectResponse(orderEntity);
	}
	
	public OrderObjectResponse convertToOrderObjectResponse(OrderEntity order) {
		OrderObjectResponse response = new OrderObjectResponse();
		
		InforRequest infor = new InforRequest();
		response.setIdOrder(order.getId());
		infor.setEmail(order.getEmail());
		infor.setFullname(order.getFullname());
		infor.setPhone(order.getPhone());
		response.setInfor(infor);
		response.setCreateDate(order.getCreateDate());
		response.setTotalOrder(order.getTotal());
		
		List<OrderItemResponse> lstItem = new ArrayList<>();
		
		for (OrderItemEntity orderItem : orderItemRepository.findAllByOrderOrderItemId(order.getId())) {
			OrderItemResponse item = new OrderItemResponse();
			item.setBookingDate(orderItem.getBookingDate());
			item.setCreateDate(orderItem.getCreateDate());
			item.setIdService(orderItem.getServiceOrderItem().getId());
			item.setTotalItem(orderItem.getTotal());
			List<TicketResponse> tickets = new ArrayList<>(); 
			for (OrderItemByTicketEntity itembyticket : orderItemByTicketRepository.findAllByOrderItemById(orderItem.getId())) {
				TicketResponse ticket = new TicketResponse();
				ticket.setAmountTicket(itembyticket.getAmount());
				ticket.setIdTicket(itembyticket.getTicketBy().getId());
				ticket.setNote(ticketRepository.findOneById(itembyticket.getTicketBy().getId()).getNote());
				ticket.setTypeTicket(itembyticket.getType());
				ticket.setValueTicket(itembyticket.getCurrentPrice());
				tickets.add(ticket);
			}
			item.setTickets(tickets);
			lstItem.add(item);	
		}
		response.setItems(lstItem);
		
		return response;
	}
}
