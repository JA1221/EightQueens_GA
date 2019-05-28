class table implements Comparable{
	int board[];
	static char Icon[] = {'��', '�D'};

//**********************�غc�l**********************************
	table(int n){
		board = new int[n];

		for(int i = 0; i < board.length; i++)
			board[i] = (int)(Math.random()*board.length);
	}

	table(int n[]){
		board = n;
	}

//��ܴѽL
	void showBoard(){
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board.length; j++){
				if(board[j] == i)
					System.out.print(Icon[0]);
				else
					System.out.print(Icon[1]);
			}
			System.out.println();
		}
		for(int i = 0; i < board.length; i++)
			System.out.print(conflict(i, board[i]) + " ");
		System.out.println("\n" + "�Ĭ��" + conflictAll());
	}

//*�^�� ����Ĭ��(��, �C) 
	int conflict(int x, int y){
		int n = 0;

		for(int i = 0; i < board.length; i ++){//��C�氻�� ���F�ۤv
			if(i == x) continue;

			if(y == board[i])//�
				n++;
			else if(Math.abs(x-i) == Math.abs(y - board[i]))//�׼�
				n++;
		}
		return n;
	}
//*�^�� �ѽL�Ĭ��
	int conflictAll(){
		int n = 0;

		for(int i = 0; i < board.length; i ++)
			n += conflict(i, board[i]);

		return n/2;
	}
//�Ƽgarrays sort
	@Override
	public int compareTo(Object newTable) {
		return Integer.compare(this.conflictAll(),((table)newTable).conflictAll());
	}	
}