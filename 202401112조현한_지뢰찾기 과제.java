package hyunhan;
import java.util.Random; //랜덤 모듈로 지뢰 배치하기
import java.util.Scanner; 

public class Main {
    private final int rows; //게임판의 행수
    private final int cols; //게임판의 열수
    private final int totalMines; //지로 총 개수
    private final char[][] board;      // Visible board for the player (플레이어에게 보이는 판)
    private final int[][] mineBoard;   // Underlying board: -1 for mine, otherwise number of adjacent mines
    private final boolean[][] revealed; //칸이 열렸는지 체크하는 불리언
    private final boolean[][] flagged; //깃발이 꽂혔는지 확인하는 불릴언

    public Main(int size, int totalMines) { //게임판 크기와 전체 지뢰수를 초기화하는 생성자.
        this.rows = size; //행
        this.cols = size; //열
        this.totalMines = totalMines; //전체 지뢰 개수
        board = new char[size][size]; // 게임판 배열 개수
        mineBoard = new int[size][size]; // 지뢰 배열 만들기
        revealed = new boolean[size][size]; //드러난 거
        flagged = new boolean[size][size]; //깃발 꽂힌 거
        initializeBoard();
        placeMines();
        calculateNumbers();
    };
    
    // Initialize the visible board with default characters.(게임판을 다 '.'로 초기화하기)ㅎ
    private void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = '.';
            }
        }
    }
    
    // Randomly place mines on the board.(지뢰를 랜덤으로 배치하는 함수 만들기 ㅎㅎ)
    private void placeMines() {
        Random rand = new Random();
        int placedMines = 0;
        while (placedMines < totalMines) {
            int r = rand.nextInt(rows);
            int c = rand.nextInt(cols);
            if (mineBoard[r][c] != -1) {
                mineBoard[r][c] = -1;
                placedMines++;
            }
        }
    }
    
    private void calculateNumbers() {
    	for (int i = 0; i < rows; i++) {
    		for (int j = 0 ;  j < cols ; j++) {
    			if (mineBoard[i][j] != -1) {
    				int count = 0; //지뢰개수 카운트하기
    				//주변 8칸을 다 체크하는 반복문이요
    				for (int di = -1; di <= 1; di++) { // 행 이동 (-1, 0, 1)
                        for (int dj = -1; dj <= 1; dj++) { // 열 이동 (-1, 0, 1)
                            int ni = i + di;
                            int nj = j + dj;
                            // 게임판 범위 안에 있고 지뢰면 카운트 증가
                            if (ni >= 0 && ni < rows && nj >= 0 && nj < cols && mineBoard[ni][nj] == -1) {
                                count++;
                            }
                        }
                    }
                    mineBoard[i][j] = count; // 계산한 숫자를 해당 칸에 저장
                }
            }
        }
    }
    				
    // Clear the console output using ANSI escape sequences. //이건 교수님이 써 준 코드.....
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Display the current state of the visible board.
    private void printBoard() {
        // Print column indices.
        System.out.print("   ");
        for (int j = 0; j < cols; j++) {
            System.out.print(j + " ");
        }
        System.out.println();
        System.out.println("  -----------------");
        for (int i = 0; i < rows; i++) {
            System.out.print(i + " | ");
            for (int j = 0; j < cols; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Main game loop.
    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean gameRunning = true;
        clearScreen();
        while (gameRunning) {
            printBoard();
            System.out.print("Enter command (r row col to reveal, f row col to flag): ");
            String command = scanner.next();
            int r = scanner.nextInt();
            int c = scanner.nextInt();
            
            if (command.equals("r")) { // 'r'이면 칸 열기
                if (mineBoard[r][c] == -1) { // 지뢰면
                    board[r][c] = '*'; // 지뢰 표시
                    printBoard(); // 최종 판 출력
                    System.out.println("게임 오바 ㅜㅜ"); // 게임 오버 메시지
                    gameRunning = false; // 게임 끝
                } else { // 지뢰 아니면
                    revealed[r][c] = true; // 칸 열림 표시
                    board[r][c] = (char) (mineBoard[r][c] + '0'); 
                }
            } else if (command.equals("f")) { 
                flagged[r][c] = !flagged[r][c]; // 깃발 있으면 제거, 없으면 추가
                board[r][c] = flagged[r][c] ? 'F' : '.'; // 'F'로 바꾸거나 '.'로 복구
            }

            clearScreen();
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("The field size: ");
        int fieldSize = scanner.nextInt();
        System.out.print("The number of mines: ");
        int numMines = scanner.nextInt();
        // Initialize a 9x9 board with 10 mines.
        Main game = new Main(fieldSize, numMines);
        game.play();
        scanner.close();
    }
}
