package pretest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class GuavaStudy {

	@Test
	public void Guava_라이브러리를_활용해_LIST를_생성한다() throws Exception {
		List<String> teamMemberJavaOriginalList = new ArrayList<String>();
		teamMemberJavaOriginalList.add("김혜원");
		teamMemberJavaOriginalList.add("우재우");
		teamMemberJavaOriginalList.add("이경륜");
		teamMemberJavaOriginalList.add("하태호");
		assertEquals("[김혜원, 우재우, 이경륜, 하태호]", teamMemberJavaOriginalList.toString());

		List<String> teamMemberJava7List = new ArrayList<>();
		teamMemberJava7List.add("김혜원");
		teamMemberJava7List.add("우재우");
		teamMemberJava7List.add("이경륜");
		teamMemberJava7List.add("하태호");
		assertEquals("[김혜원, 우재우, 이경륜, 하태호]", teamMemberJava7List.toString());

		List<String> teamMemberGuavaList = Lists.newArrayList();
		teamMemberGuavaList.add("김혜원");
		teamMemberGuavaList.add("우재우");
		teamMemberGuavaList.add("이경륜");
		teamMemberGuavaList.add("하태호");
		assertEquals("[김혜원, 우재우, 이경륜, 하태호]", teamMemberGuavaList.toString());
	}

	@Test
	public void Guava_라이브러리를_활용해_SET을_생성한다() throws Exception {
		Set<String> javaOriginalGrade = new HashSet<String>();
		javaOriginalGrade.add("A");
		javaOriginalGrade.add("B");
		javaOriginalGrade.add("C");
		javaOriginalGrade.add("D");
		javaOriginalGrade.add("F");
		assertEquals("[A, B, C, D, F]", javaOriginalGrade.toString());
		
		Set<String> java7Set = new HashSet<>();
		java7Set.add("A");
		java7Set.add("B");
		java7Set.add("C");
		java7Set.add("D");
		java7Set.add("F");
		assertEquals("[A, B, C, D, F]", java7Set.toString());
		
		Set<String> guavaSet = Sets.newHashSet();
		guavaSet.add("A");
		guavaSet.add("B");
		guavaSet.add("C");
		guavaSet.add("D");
		guavaSet.add("F");
		assertEquals("[A, B, C, D, F]", guavaSet.toString());
	}

	@Test
	public void Guava_라이브러리를_활용해_MAP을_생성한다() throws Exception {
		Map<String, String> tutorProfessorJavaOriginalMap = new HashMap<String, String>();
		tutorProfessorJavaOriginalMap.put("김혜원", "배영현");
		tutorProfessorJavaOriginalMap.put("우재우", "오동우");
		tutorProfessorJavaOriginalMap.put("이경륜", "함석진");
		tutorProfessorJavaOriginalMap.put("하태호", "김동진");

		Map<String, String> tutorProfessoJava7Map = new HashMap<>();
		tutorProfessoJava7Map.put("김혜원", "배영현");
		tutorProfessoJava7Map.put("우재우", "오동우");
		tutorProfessoJava7Map.put("이경륜", "함석진");
		tutorProfessoJava7Map.put("하태호", "김동진");
		
		Map<String, String> tutorProfessoGuavaMap = Maps.newHashMap();
		tutorProfessoGuavaMap.put("김혜원", "배영현");
		tutorProfessoGuavaMap.put("우재우", "오동우");
		tutorProfessoGuavaMap.put("이경륜", "함석진");
		tutorProfessoGuavaMap.put("하태호", "김동진");
		
		assertEquals(tutorProfessorJavaOriginalMap, tutorProfessoJava7Map);
		assertEquals(tutorProfessoJava7Map, tutorProfessoGuavaMap);
		assertEquals(tutorProfessoGuavaMap, tutorProfessorJavaOriginalMap);
	}
}
