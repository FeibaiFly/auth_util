import java.util.ArrayList;
import java.util.List;

public class Test2 {
    public static  void main(String[] args) {
        String str = "175.00,160.00,150.00,145.00,135.00,125.00,120.00,110.00,100.00,95.00,85.00,80.00,70.00,60.00,55.00,45.00,35.00,325.00,315.00,310.00,300.00,290.00,285.00,275.00,265.00,260.00,255.00,245.00,235.00,225.00,215.00,205.00,200.00,190.00,185.00,180.00,170.00,155.00,140.00,135.00,125.00,120.00,108.00,96.00,81.00,66.00,351.00,341.00,336.00,331.00,319.00,309.00,299.00,284.00,269.00,254.00,249.00,234.00,224.00,219.00,207.00,202.00,190.00,185.00,173.00,158.00,153.00,141.00,126.00,121.00,106.00,91.00,386.00,371.00,356.00,351.00,339.00,334.00,324.00,319.00,307.00,302.00,287.00,272.00,267.00,257.00,242.00,230.00,215.00,200.00,195.00,183.00,168.00,163.00,148.00,136.00,131.00,119.00,104.00,92.00,80.00,65.00,60.00,48.00,33.00,528.00,516.00,504.00,499.00,487.00,475.00,470.00,455.00,443.00,438.00,423.00,418.00,406.00,391.00,386.00,371.00,356.00,351.00,339.00,324.00,319.00,307.00,295.00,290.00,275.00,260.00,248.00,233.00,218.00,213.00,201.00,191.00,186.00,174.00,166.00,162.50,154.00,139.00,134.00,127.00,112.00,100.00,95.00,83.00,68.00,53.00,238.00,233.00,218.00,206.00,201.00,191.00,186.00,171.00,156.00,151.00,139.00,127.00,122.00,110.00,95.00,90.00,78.00,63.00,58.00,43.00,28.00,23.00,11.00,199.00,194.00,189.00,174.00,162.00,157.00,142.00,127.00,122.00,107.00,95.00,80.00,75.00,63.00,248.00,243.00,238.00,233.00,218.00,203.00,198.00,183.00,171.00,166.00,154.00,139.00,134.00,119.00,107.00,92.00,77.00,62.00,47.00,32.00,27.00,222.00,207.00,195.00,190.00,175.00,160.00,155.00,143.00,131.00,119.00,114.00,104.00,89.00,84.00,69.00,64.00,49.00,39.00,234.00,222.00,207.00,202.00,187.00,175.00,160.00,145.00,140.00,128.00,113.00,108.00,93.00,78.00,73.00,268.00,253.00,238.00,226.00,211.00,206.00,191.00,176.00,171.00,156.00,146.00,141.00,126.00,111.00,99.00,84.00,69.00,57.00,52.00,40.00,35.00,23.00,11.00,506.00,494.00,489.00,484.00,469.00,454.00,449.00,444.00,429.00,414.00,409.00,394.00,379.00,369.00,357.00,342.00,337.00,332.00,317.00,307.00,302.00,297.00,287.00,272.00,267.00,255.00,245.00,240.00,228.00,213.00,208.00,203.00,188.00,173.00,168.00,156.00,141.00,136.00,121.00,109.00,99.00,94.00,82.00,77.00,72.00,67.00,52.00,237.00,225.00,220.00,205.00,193.00,188.00,176.00,161.00,146.00,131.00,126.00,114.00,102.00,97.00,85.00,73.00,68.00,53.00,41.00,26.00,21.00,11.00,196.00,181.00,166.00,161.00,156.00,144.00,139.00,127.00,112.00,107.00,95.00,80.00,68.00,56.00,51.00,39.00,234.00,222.00,207.00,192.00,187.00,175.00,160.00,155.00,140.00,135.00,120.00,105.00,90.00,78.00,66.00,51.00,46.00,34.00,222.00,217.00,202.00,187.00,182.00,170.00,158.00,153.00,138.00,123.00,118.00,103.00,88.00,83.00,71.00,56.00,41.00,236.00,221.00,216.00,204.00,199.00,184.00,172.00,157.00,152.00,140.00,125.00,120.00,105.00,100.00,85.00,73.00,58.00,53.00,41.00,26.00,21.00,206.00,201.00,186.00,181.00,166.00,161.00,146.00,131.00,126.00,111.00,96.00,84.00,72.00,60.00,45.00,35.00,30.00,15.00,200.00,188.00,176.00,166.00,161.00,149.00,134.00,122.00,110.00,105.00,90.00,78.00,266.00,261.00,249.00,244.00,229.00,217.00,212.00,200.00,188.00,183.00,171.00,159.00,154.00,142.00,127.00,122.00,107.00,92.00,87.00,72.00,57.00,52.00,40.00,25.00,220.00,208.00,193.00,181.00,166.00,151.00,146.00,131.00,116.00,111.00,106.00,91.00,86.00,71.00,56.00";
        String[] arr = str.split(",");
        List<Integer> num = new ArrayList<>();
        String lastStr = "";
        for(int i=0;i<arr.length;i++){
            String s = arr[i];
            if(s.equals(lastStr)) {
                System.out.println(lastStr);
                System.out.println(i);
            }
            lastStr = s;
        }


    }
}
