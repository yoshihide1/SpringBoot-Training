package com.example.training.common.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.training.common.repository.OrderRepository;

@Service
public class OrderAssemblerService {

	@Autowired
	OrderRepository orderRepository;

	public Map<Integer, List<OrderMonth>> create(List<OrderMonth> orders) {
		Map<Integer, List<OrderMonth>> map = new TreeMap<Integer, List<OrderMonth>>();
		for (OrderMonth order : orders) {
			// 月の取得
			int month = order.getDate().getMonthValue();
			// 既にキーとして存在していたら配列に値を追加
			if (map.containsKey(month)) {
				map.get(month).add(order);
				// 存在していなかったら新しくキーと値を追加
			} else {
				map.put(month, new ArrayList<>());
				map.get(month).add(order);
			}
		}
		return map;
	}
}
