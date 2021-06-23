package com.qian.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qian.service.DataService;

@Controller
@RequestMapping("/Test")
public class TestController {

	@Autowired
	private DataService dataService;
	
	@RequestMapping("/DigitalArray")
	public String index() {
		return "addTrainDataTest";
	}
	@RequestMapping("/getData")
	public String getData(@RequestParam(value = "file", required = false) List<MultipartFile> file, HttpServletRequest req) {
		dataService.getData(file, req);
		return "redirect:/Test/points1000Check";
	}
	/*
	 * 数据1000点校改
	 */
	@RequestMapping("/points1000Check")
	public String points1000Check() {
		dataService.points1000Check();
		return "redirect:/Test/splitData";
	}
	/*
	 * 进行数据的分割
	 */
	@RequestMapping("/splitData")
	@ResponseBody
	public String splitData(HttpServletResponse resp) {
		dataService.splitData();
		dataService.MotivationCnt += 1;
		return "Test splitData successgfully!";
	}
}
