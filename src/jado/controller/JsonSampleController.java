package jado.controller;

import jado.model.Dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonSampleController {
	
	@RequestMapping(value = "/json", method = RequestMethod.GET)
	public Dummy getJson(String name) {
		return new Dummy(1, "dummy");
	}
	
	@RequestMapping(value = "/json2", method = RequestMethod.GET)
	public Map<String, Object> getJsonMap() {
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		List<Dummy> list = makeSampleList();
		jsonMap.put("data", list);
		
		return jsonMap;
	}

	private List<Dummy> makeSampleList() {
		List<Dummy> list = new ArrayList<Dummy>();
		list.add(new Dummy(1, "dummy"));
		list.add(new Dummy(2, "dummy2"));
		list.add(new Dummy(3, "dummy3"));
		return list;
	}
}
