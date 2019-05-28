import java.util.Scanner;
import java.util.Arrays;

class EightQueens_GA{
	static Scanner scanner= new Scanner(System.in);
	static final int boardNum = 100;
	static final int POP_len = 10;
	static table POP[] = new table[POP_len];
	static int mutateP = 50;
	static int mutateN = 0;

//crossOver new���� = ����x�e + ����y��
	static table crossOver(table x, table y){
		int board[] = new int[boardNum];

		for(int i = 0; i < boardNum; i ++){
			if(i < boardNum / 2)
				board[i] = x.board[i];
			else
				board[i] = y.board[i];
		}
		//���v�� �H���@�Ӧ�m����
		if((int)(Math.random()*100) < mutateP){
			mutateN++;
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

		//�ͦ�
		for(int i = 0; i < POP_len; i++){
			POP[i] = new table(boardNum);
			// POP[i].showBoard();
		}

		int Generation = 0;
		boolean ansFlag = false;

		while(!ansFlag){
			mutateN = 0;

			table newPOP[] = new table[POP_len*(POP_len-1)];//�إ߷s�s�� n*(n*1)
			int address = 0;
			
			//��Ĭ��==0 �ѥX
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

			//��]�Ƨ�
			Arrays.sort(newPOP);
			//���X�u�q��
			for(int i = 0; i < POP_len; i ++)
				POP[i] = newPOP[i];

			System.out.print("Generation:" + Generation);
			System.out.println(" <-> mutate:" + mutateN + ", Best:" + POP[0].conflictAll());
			Generation++;
		}

		System.out.println("Using Time:" + (System.currentTimeMillis() - startTime) + " ms");
	}
}