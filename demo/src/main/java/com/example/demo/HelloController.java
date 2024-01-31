package com.example.demo;

import java.util.HashMap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

// Restful API - 데이터를 주고 받을 떄 xml 혹은 json 사용한다
// xml - 실제 데이터를 가져오는 parsing 과정이 별도로 필요하

// http://localhost:8080/getUserInfo
// 기본 포트 8080

// 클라이언트에서의 요청을 허용한
@CrossOrigin("*")
// java 객체를 json 형태로 전환해준다
@RestController
// /hello/~ 형태는 이 Controller가 전부 처리한다
@RequestMapping(value="")
public class HelloController {
	// cmd+shift+o
	@GetMapping("/getUserInfo")
	public HashMap<String, String> getUserInfo() {
		// Collection Class - 배열, Map, json, SortedList ...
		// 배열의 요소는 index를 통해 읽고 쓸 수 있다
		// HashMap, Dictionary, json - 키와 값으로 구성되는 데이터를 저장해서
		// 데이터를 읽고 쓸 때 키 값을 찾아서 읽고 쓰
		HashMap<String, String> map = new HashMap<String, String>();
		
		// {"name": "홍길동", "phone" "010-9000-0001", "address": "서울시 관악구"}
		map.put("name", "홍길동");
		map.put("phone", "010-9000-0001");
		map.put("address", "서울시 관악구");
		
		return map;
	}
	
	// 정보를 주고 받는 방식 - get 방식
	// getUserInfo?userid=test&username="홍길동"
	
	// 새로운 방식
	// /getUserInfo/test
	
	// post 방식 -> form 태그에 method="POST"로 바꿔야 한다
	
	// add1?x=5&y=7 {x:5, y:7, result:12} - get
	@GetMapping("/add1")
	public HashMap<String, Object> add1(HttpServletRequest request, 
			@RequestParam("x") int x, @RequestParam("y") int y) {
		// HttpServletRequest 객체에 담아온다
		// int x = Integer.parseInt(request.getParameter("x"));
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("x", x);
		map.put("y", y);
		map.put("result", x+y);
		
		return map;
	}
	
	// add2/5/7 {x:5, y:7, result:12} - get
	@GetMapping("/add2/{x}/{y}")
	public HashMap<String, Object> add2(HttpServletRequest request, 
			@PathVariable("x") int x, @PathVariable("y") int y) {
		// HttpServletRequest 객체에 담아온다
		// int x = Integer.parseInt(request.getParameter("x"));
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("x", x);
		map.put("y", y);
		map.put("result", x+y);
		
		return map;
	}
	
	// add3 {x:5, y:7, result:12} - post
	@PostMapping("/add3")
	public HashMap<String, Object> add3(HttpServletRequest request, 
			@RequestParam("x") int x, @RequestParam("y") int y) {
		// HttpServletRequest 객체에 담아온다
		// int x = Integer.parseInt(request.getParameter("x"));
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("x", x);
		map.put("y", y);
		map.put("result", x+y);
		
		return map;
	}
	
	@PostMapping("/add4")
	// @RequestBody - client가 데이터를 json 형태로 보낼 때
	// json 데이터를 받아서 자바 객체로 변환하는 과정을 거친다
	// HashMap이나 DTO(Data Transfer Object) 클래스
	// DB 테이블 필드와 거의 1:1
	// 클라이언트로부터 파라미터를 받아올 때 DTO를 사용한다
	public HashMap<String, Object> add4(@RequestBody HashMap<String, Object> map) {
		// HttpServletRequest 객체에 담아온다
		// int x = Integer.parseInt(request.getParameter("x"));
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		int x = Integer.parseInt(map.get("x").toString());
		int y = Integer.parseInt(map.get("y").toString());
		
		resultMap.put("x", x);
		resultMap.put("y", y);
		resultMap.put("result", x+y);
		
		return resultMap;
	}
}
