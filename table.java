class table implements Comparable{
	int board[];
	static char Icon[] = {'●', '．'};

//**********************建構子**********************************
	table(int n){
		board = new int[n];

		for(int i = 0; i < board.length; i++)
			board[i] = (int)(Math.random()*board.length);
	}

	table(int n[]){
		board = n;
	}

//顯示棋盤
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
		System.out.println("\n" + "衝突數" + conflictAll());
	}

//*回傳 此行衝突數(行, 列) 
	int conflict(int x, int y){
		int n = 0;

		for(int i = 0; i < board.length; i ++){//跟每行偵測 除了自己
			if(i == x) continue;

			if(y == board[i])//橫撞
				n++;
			else if(Math.abs(x-i) == Math.abs(y - board[i]))//斜撞
				n++;
		}
		return n;
	}
//*回傳 棋盤衝突數
	int conflictAll(){
		int n = 0;

		for(int i = 0; i < board.length; i ++)
			n += conflict(i, board[i]);

		return n/2;
	}
//複寫arrays sort
	@Override
	public int compareTo(Object newTable) {
		return Integer.compare(this.conflictAll(),((table)newTable).conflictAll());
	}	
}