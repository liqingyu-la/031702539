import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sudoku {
    private int[][] matrix;
    private static String inputFileName = null;
    private static String outputFileName = null;
 
	public  Sudoku(int[][] matrix) {
        this.matrix = matrix;
    }

    public static void main(String[] args) {
    	
    	int m = 0,n;//宫格、盘面

    	
    	/*if(args.length>0 && args!=null ) {
    		for(int i=0; i<args.length; i++) {
    			switch(args[i]) {
    			case "-m":
    				m = Integer.valueOf(args[i+1]);
    				break;
    			case "-n":
    				n = Integer.valueOf(args[i+1]);
    				break;
    			case "-i":
    				inputFileName = args[i+1];
    				break;
    			case "-o":
    				outputFileName = args[i+1];
    				break;
    			}
    		}
    	} */ 	
    	
    	//文件路径
    	
        try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw  

            /* 读入TXT文件 */  
            // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径  
            File filename = new File("input.txt"); // 要读取以上路径的input.txt文件  
            InputStreamReader reader = new InputStreamReader(  
                    new FileInputStream(filename),"UTF-8"); // 建立一个输入流对象reader  ****
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
            
            //把盘面写入数组
            int ch;
            while((ch=br.read())!=-1){//改进在这里
            	/*if((char)ch!='\n' && (char)ch!=' ') {
            		for(int i=0; i<m; i++)
            			for(int j=0; j<m; j++) {
            				String b = String.valueOf(ch);
            				sudoku[i][j] = Integer.parseInt(b);
            				 
             			}
            	}*/
            	
        	}

            /* 写入TXT文件 */  
            File writename = new File("output.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件  
            writename.createNewFile(); // 创建新文件  
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));  
            out.write("我会写入文件啦\r\n"); // \r\n即为换行  
            out.flush(); // 把缓存区内容压入文件  
            out.close(); // 最后记得关闭文件 /* */

        } catch (Exception e) {  
            e.printStackTrace();  
        } 
 	
    	
        // 号称世界上最难数独
        int[][] sudoku = {
                {0, 5, 0, 0, 0, 0, 0, 2, 0},
                {4, 0, 0, 2, 0, 6, 0, 0, 7},
                {0, 0, 8, 0, 3, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 6, 0},
                {0, 0, 9, 0, 0, 0, 5, 0, 0},
                {0, 7, 0, 0, 0, 0, 0, 9, 0},
                {0, 0, 5, 0, 8, 0, 3, 0, 0},                
                {7, 0, 0, 9, 0, 1, 0, 0, 4},
                {0, 2, 0, 0, 0, 0, 0, 7, 0}};/**/
        
        Sudoku s = new Sudoku(sudoku);
        s.backTrace(0, 0);
    }
 
    /**
     * 数独算法
     *
     * @param i 行号
     * @param j 列号
     */
    private void backTrace(int i, int j) {
        if (i == 8 && j == 9) {
            //已经成功了，打印数组即可
            System.out.println("获取正确解");
            printArray();
            return;
        }
 
        //已经到了列末尾了，还没到行尾，就换行
        if (j == 9) {
            i++;
            j = 0;
        }
 
        //如果i行j列是空格，那么才进入给空格填值的逻辑
        if (matrix[i][j] == 0) {
            for (int k = 1; k <= 9; k++) {
                //判断给i行j列放1-9中的任意一个数是否能满足规则
                if (check(i, j, k)) {
                    //将该值赋给该空格，然后进入下一个空格
                    matrix[i][j] = k;
                    backTrace(i, j + 1);
                    //初始化该空格
                    //System.out.println("初始化");
                    matrix[i][j] = 0;
                }
            }
        } else {
            //如果该位置已经有值了，就进入下一个空格进行计算
            backTrace(i, j + 1);
        }
    }
 
    /**
     * 判断给某行某列赋值是否符合规则
     *
     * @param row    被赋值的行号
     * @param line   被赋值的列号
     * @param number 赋的值
     * @return
     */
    private boolean check(int row, int line, int number) {
        //判断该行该列是否有重复数字
        for (int i = 0; i < 9; i++) {
            if (matrix[row][i] == number || matrix[i][line] == number) {
                return false;
            }
        }
        //判断小九宫格是否有重复
        int tempRow = row / 3;
        int tempLine = line / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix[tempRow * 3 + i][tempLine * 3 + j] == number) {
                    return false;
                }
            }
        }
 
        return true;
    }
 
    /**
     * 打印矩阵s
     */
    public void printArray() {
    	
    	
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    
    
}

