package algo_0209;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_3190 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[][] arr = new int[N+2][N+2];
		
		int apple = Integer.parseInt(in.readLine());
		for(int k = 0; k < apple; k++) {
			String str = in.readLine();
			StringTokenizer st = new StringTokenizer(str, " ");
			
			arr[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 2;
		}
		
		int L = Integer.parseInt(in.readLine());
		Queue<String> turn = new LinkedList<String>();

		for(int i = 0; i < L; i++) {
			String str2 = in.readLine();
			StringTokenizer st2 = new StringTokenizer(str2, " ");
			
			turn.offer(st2.nextToken());
			turn.offer(st2.nextToken());
		}
		
		int cnt = 0;
		int dx[] = {0, 1, 0, -1}; //���ƿ���
		int dy[] = {1, 0, -1, 0};
		int direct = 0;
		int x = 1, y = 1;
		
		Queue<int[]> snake = new LinkedList<int[]>();
		snake.offer(new int[] {1,1});
		
		while(true) {
			if(!turn.isEmpty()) {
				if(cnt == Integer.parseInt(turn.peek())) {
					turn.poll();
					String turnDirect = turn.poll();
					if(turnDirect.equals("L")) {
						direct--;
						if(direct < 0)
							direct += 4;
					}else {
						direct++;
						if(direct == 4)
							direct = 0;
					}
				}
			}

			//�迭 ����� break
			if(x + dx[direct] == N+1 || x + dx[direct] == 0 || y + dy[direct] == N+1 || y + dy[direct] == 0)
				break;
			
			//�Ӹ��� ���̶� ������ break
			if(arr[x + dx[direct]][y + dy[direct]] == 1)
				break;
			
			//����� �ִ��� ������ üũ
			if(arr[x + dx[direct]][y + dy[direct]] != 2) {
				//������ ��ĭ ����
				arr[x + dx[direct]][y + dy[direct]] = 1;
				//ť�� �߰�
				snake.offer(new int[] {x + dx[direct], y + dy[direct]});
				int[] passed = snake.poll();
				arr[passed[0]][passed[1]] = 0;
			}else {
				arr[x + dx[direct]][y + dy[direct]] = 1;
				snake.offer(new int[] {x + dx[direct], y + dy[direct]});
			}
			
			cnt++;
			x += dx[direct];
			y += dy[direct];
		}
		
		System.out.println(cnt+1);
	}
	
}

