package 자료구조_11장_그래프;

import java.util.*;

// 그래프는 배열 씀

/*
문제 설명
네트워크란 컴퓨터 상호 간에 정보를 교환할 수 있도록 연결된 형태를 의미합니다.
예를 들어, 컴퓨터 A와 컴퓨터 B가 직접적으로 연결되어있고, 컴퓨터 B와 컴퓨터 C가 직접적으로 연결되어 있을 때 컴퓨터 A와 컴퓨터 C도 간접적으로 연결되어 정보를 교환할 수 있습니다.
따라서 컴퓨터 A, B, C는 모두 같은 네트워크 상에 있다고 할 수 있습니다.

컴퓨터의 개수 n, 연결에 대한 정보가 담긴 2차원 배열 computers가 매개변수로 주어질 때, 네트워크의 개수를 return 하도록 solution 함수를 작성하시오.

제한사항
컴퓨터의 개수 n은 1 이상 200 이하인 자연수입니다.
각 컴퓨터는 0부터 n-1인 정수로 표현합니다.
i번 컴퓨터와 j번 컴퓨터가 연결되어 있으면 computers[i][j]를 1로 표현합니다.
computer[i][i]는 항상 1입니다.
입출력 예
n	computers	return
3	[[1, 1, 0], [1, 1, 0], [0, 0, 1]]	2
3	[[1, 1, 0], [1, 1, 1], [0, 1, 1]]	1

110 // 첫 번째 컴퓨터
110 // 두 번째 컴퓨터
001 // 세 번째 컴퓨터
(대각선 111은 본인 => 따라서 네트워크는 두 개)
(1-2 컴퓨터 연결 o / 3 컴퓨터 연결 x)

110
111
011
(대각선 111은 본인 => 네트워크는 한 개)
(1-2 연결 o / 2-3 연결 o / 따라서 1-2-3 연결)
*/

public class Soulution43162 {
	public static int solution(int n, int[][] computers) {
		// DFS는 경우의 수 (배열 다 찾기) => 모든 것을 구해야 하나요?
		// BFS는 배열 하나만 찾기 => 최단 거리를 구해야 하나요?
		// 이 문제는 DFS (1. 재귀 2. 스택 3. 큐)
		// 1. Stack으로 풀기
		// - 메서드 만들기
		// - 매개변수 확인하기(연결에 대한 정보 computers, 방문기록 visited, 무방향이라 가는 길 하나 i)
		
        int answer = 0; // 메서드 호출
        return answer;
    }
	
	private static int solutionStack(int n, int[][] computers) {
		// 2. 설계
		int networkCount = 0;
		boolean[] visited = new boolean[n];
		
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				dfsStack(computers, visited, i);
				networkCount++;
			}
		}
		return networkCount;
	}
	
	private static void dfsStack(int[][] computers, boolean[] visited, int start) {
		Stack<Integer> stack = new Stack<>();
		stack.push(start);
		visited[start] = true;
		
		// 깊이 우선 탐색
		while (!stack.isEmpty()) {
			int current = stack.pop();
			for (int i = 0; i < computers.length; i++) {
				if (computers[current][i] == 1 && !visited[i]) {
					stack.push(i);
					visited[i] = true;
				}
			}
		}
	}
	
	private static int solutionQueue(int n, int[][] computers) {
		// 2. 설계
		int networkCount = 0;
		boolean[] visited = new boolean[n];
		
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				dfsQueue(computers, visited, i);
				networkCount++;
			}
		}
		return networkCount;
	}
	
	private static void dfsQueue(int[][] computers, boolean[] visited, int start) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true;
		
		// 깊이 우선 탐색
		while (!queue.isEmpty()) {
			int current = queue.poll();
			for (int i = 0; i < computers.length; i++) {
				if (computers[current][i] == 1 && !visited[i]) {
					queue.offer(i);
					visited[i] = true;
				}
			}
		}
	}

	public static void main(String[] args) {
		int n1 = 3;
		int[][] computers1 = {
				{1, 1, 0},
				{1, 1, 0},
				{0, 0, 1}
		};
		
		
		int n2 = 3;
		int[][] computers2 = {
				{1, 1, 0},
				{1, 1, 1},
				{0, 1, 1}
		};
		
		System.out.println("DFS Case1: " + solutionStack(n1, computers1));
		System.out.println("DFS Case2: " + solutionStack(n2, computers2));
		
		System.out.println("DFS Case1(Queue): " + solutionQueue(n1, computers1));
		System.out.println("DFS Case2(Queue): " + solutionQueue(n2, computers2));
	}
}
