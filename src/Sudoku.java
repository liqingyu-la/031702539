import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class Sudoku {
	int m;
	private static String inputFileName;
	private static String outputFileName;
	static int[][] matrix = new int[10][10];
    //boolean find;
    public Sudoku(int m) {
        this.m = m;
    }

    public void outputs() {  	
    	try {
			/* 写入TXT文件 */  
            File writename = new File(outputFileName); // 相对路径，如果没有则要建立一个新的output。txt文件  
            //writename.createNewFile(); // 创建新文件  
            if(!writename.exists()){               //判断是否存在
            	writename.createNewFile();         //新建文本
            	}
            BufferedWriter out = new BufferedWriter(new FileWriter(writename,true)); 
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                	out.write(matrix[i][j] + " ");                
                }
                out.write("\r\n");  // \r\n即为换行
            }
            out.write("\r\n");
            out.flush(); // 把缓存区内容压入文件  
            out.close(); // 最后记得关闭文件  

        } catch (Exception e) {  
            e.printStackTrace();  
        } 
    	
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

        
    /**
     * 数独算法
     *
     * @param i 行号
     * @param j 列号
     */
    private void backTrace(int i, int j) {
    	
        if (i == m-1 && j == m) {
            System.out.println("获取正确解");            
            outputs();;
            return;            
        }
        //已经到了列末尾了，还没到行尾，就换行
        if (j == m) {
            i++;
            j = 0;
        } 
        //如果i行j列是空格，那么才进入给空格填值的逻辑
        if (matrix[i][j] == 0) {
        	 
            for (int k = 1; k <= m; k++) {
                //判断给i行j列放1-9中的任意一个数是否能满足规则
                if (check(i, j, k)) {
                    //将该值赋给该空格，然后进入下一个空格
                	//System.out.println( i+ " " + j + " " + k);
                    matrix[i][j] = k;
                    backTrace(i, j + 1);
                    //初始化该空格
                    //System.out.println("初始化");
                    //if(find==false)
                    matrix[i][j] = 0;
                    
                }
            }
        } else {
            //如果该位置已经有值了，就进入下一个空格进行计算
            backTrace(i, j + 1);
        }
    } 
    
     //判断给某行某列赋值是否符合规则  
    private boolean check(int row, int line, int number) {
        //判断该行该列是否有重复数字
        for (int i = 0; i < m; i++) {//3.5.7情况
            if (matrix[row][i] == number || matrix[i][line] == number) {
                return false;
            }
        }
        //判断小九宫格是否有重复
                
        if(m==6) {
        	int tempRow = row / 2;
	        int tempLine = line / 3;
	        for (int i = 0; i < 2; i++) {
	            for (int j = 0; j < 3; j++) {
	                if (matrix[tempRow * 2 + i][tempLine * 3 + j] == number) {
	                    return false;
	                }
	            }
	        }
        }
        else if(m==8) {
        	int tempRow = row / 4;
	        int tempLine = line / 2;
	        for (int i = 0; i < 4; i++) {
	            for (int j = 0; j < 2; j++) {
	                if (matrix[tempRow * 4 + i][tempLine * 2 + j] == number) {
	                    return false;
	                }
	            }
	        }
        }
        else if(m==4) {
        	int tempRow = row / 2;
	        int tempLine = line / 2;
	        for (int i = 0; i < 2; i++) {
	            for (int j = 0; j < 2; j++) {
	                if (matrix[tempRow * 2 + i][tempLine * 2 + j] == number) {
	                    return false;
	                }
	            }
	        }
        }
        else if(m==9) {
	        int tempRow = row / 3;
	        int tempLine = line / 3;
	        for (int i = 0; i < 3; i++) {
	            for (int j = 0; j < 3; j++) {
	                if (matrix[tempRow * 3 + i][tempLine * 3 + j] == number) {
	                    return false;
	                }
	            }
	        }
        }
        
 
        return true;
    }
   
    /*public void printArray() {
    	
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }*/
 
    public static void main(String[] args) {  	
    	int m = 6,n = 2;//宫格、盘面   
    	//String inputFileName = null;
	    //String outputFileName = null;
    	if(args.length>0 && args!=null ) {
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
    			default:
    				break;
    			}
    		}  
    	}
    	
    	try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw  
            /* 读入TXT文件 */  
            // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径  
            File filename = new File(inputFileName); // 要读取以上路径的input.txt文件  
            InputStreamReader reader = new InputStreamReader(  
                    new FileInputStream(filename),"UTF-8"); // 建立一个输入流对象reader  ****
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言             
            //把盘面写入数组                            	  	    	
    	    	int ch;
    	        int i=0; int j=0;
    	        while ((ch = br.read()) != -1) {
    	            if ( (((char) ch) != '\n') &&(((char) ch) != ' ')) {  
    	            	if(i<m){
    	            		if(j<m){
    	            			if(ch!=13){
    	            				matrix[i][j]=((char) ch)-48;
    	            				j++;
    	            			}
    	            		}else{  
    	            			i++;
    	            			j=0;
    	            			matrix[i][j]=((char) ch)-48;
    	            		}
    	            	}
    	            	
    	            	 if(i==m){
    	                     if(n!=0){
    	                         Sudoku s = new Sudoku(6);
    	                         s.backTrace(0, 0);
    	                         n--;
    	                         i=0;j=0;
    	                     }
    	            	 }   	          	 	    	            	
    	           }   	    		    	            	            	                	    	    	    	
    	      }
    	      br.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }   	
    }   
    
}

