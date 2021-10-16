import java.util.Scanner;

public class Main {

	static char[][] map=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int row = sc.nextInt();
		int col = sc.nextInt();
		
		map = new char[col][row];
		sc.nextLine();
		for (int y=0;y<row;y++) {
			String line = sc.nextLine();
			for (int x=0;x<col;x++) {
				map[x][y] = line.charAt(x);
			}
		}
		
		int n = sc.nextInt();
		sc.nextLine();
		char[] instructions = new char[n];
		for (int i=0;i<n;i++) {
			instructions[i]=sc.nextLine().charAt(0);
		}
		
		for (int x=0;x<col;x++) {
			for (int y=0;y<row;y++) {
				for (int dir=0;dir<4;dir++) {
					int[] coord=go(x,y,dir, instructions);
					if (coord!=null) {
						map[coord[0]][coord[1]] = '*';
					}
				}
				
			}
		}
		
		for (int y=0;y<row;y++) {
			for (int x=0;x<col;x++) {
				System.out.print(map[x][y]);
			}
			System.out.println();
		}
	}
	
	private static int[] go(int x, int y, int dir, char[] instructions) {
		// TODO Auto-generated method stub
		int[] pos = new int[3];
		pos[0] = x;
		pos[1] = y;
		pos[2] = dir;
		for (int i=0;i<instructions.length;i++) {
			char inst = instructions[i];
			pos=step(pos[0],pos[1], pos[2], inst);
			if (pos==null) return null;
		}
		
		return pos;
	}

	// directions: R=0;U=1;L=2;D=3
	private static int[] step(int x, int y, int dir, char inst) {
		// TODO Auto-generated method stub
		int newX, newY,newDir;
		switch (inst) {
		case 'F':
			newDir=dir;
			switch (dir) {
			case 0:
				newX = x+1;
				newY = y;
				break;
			case 1:
				newX=x;
				newY=y-1;
				break;
			case 2:
				newX=x-1;
				newY=y;
			case 3:
				newX = x;
				newY = y+1;
			default:
				return null;
			}
			break;
		case 'L':
			newX=x;
			newY=y;
			newDir=(dir+1)%4;
			break;
		case 'R':
			newX=x;
			newY=y;
			newDir=(dir+3)%4;
			break;
		default: return null;
		}
		if (valid(newX,newY)) {
			int[] pos = {newX,newY,newDir};
			return pos;
		}
		return null;
	}

	private static boolean valid(int x, int y) {
		// TODO Auto-generated method stub
		return x>0 && x<map.length && y>0 && y<map[0].length && map[x][y]!='X';
	}

}
