import java.util.Scanner;
import java.util.Arrays;

class EightQueens_GA{
	static Scanner scanner= new Scanner(System.in);
	static final int boardNum = 100;
	static final int POP_len = 10;
	static table POP[] = new table[POP_len];
	static int mutateP = 30;

//crossOver new物件 = 物件x前 + 物件y後
	static table crossOver(table x, table y){
		int board[] = new int[boardNum];

		for(int i = 0; i < boardNum; i ++){
			if(i < boardNum / 2)
				board[i] = x.board[i];
			else
				board[i] = y.board[i];
		}
		//機率性 隨機一個位置突變
		if((int)(Math.random()*100) < mutateP){
			int mutate = (int)(Math.random()*boardNum);

			board[mutate] = (int)(Math.random()*boardNum);
		}
		table newT = new table(board);

		return newT;
	}

	static void sortPOP(table x[]){
		int conflict[] = new int[x.length];

		for(int i = 0; i < x.length; i++)
			conflict[i] = x[i].conflictAll();
	}
//=================================================================
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();

		//生成
		for(int i = 0; i < POP_len; i++){
			POP[i] = new table(boardNum);
			// POP[i].showBoard();
		}

		int Generation = 0;
		boolean ansFlag = false;

		while(!ansFlag){
			System.out.println("Generation:" + Generation);

			table newPOP[] = new table[POP_len*(POP_len-1)];//建立新群體
			int address = 0;
			
			//當衝突數==0 解出
			for(int i = 0; i < POP_len; i ++){
				if(POP[i].conflictAll()==0){
					POP[i].showBoard();
					ansFlag = true;
					break;
				}
			}

			//crossover
			for(int i = 0; i < POP_len; i ++){
				for(int j = i+1; j < POP_len; j++){
					newPOP[address] = crossOver(POP[i], POP[j]);
					address++;
					newPOP[address] = crossOver(POP[j], POP[i]);
					address++;
				}
			}

			//基因排序
			Arrays.sort(newPOP);
			//取出優秀者
			for(int i = 0; i < POP_len; i ++)
				POP[i] = newPOP[i];

			Generation++;
		}

		System.out.println("Using Time:" + (System.currentTimeMillis() - startTime) + " ms");
	}
}