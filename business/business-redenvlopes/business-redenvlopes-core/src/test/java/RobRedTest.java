import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * author 14526
 * create_time 2023/3/25
 */
@SpringBootTest
public class RobRedTest {

    
    /**
     //红包随机算法 二倍均值法  1 ~ 积分 / 剩余人数 * 2
     //积分：1000 剩余人数：10  ->  红包范围：1~1000/10*2 = 1~200
     //积分：900 剩余人数：9    ->  红包范围：1~900/9*2 = 1~200
     */
    @Test
    public void testRobRed(){
        int score = 500; //剩余积分
        int count = 10; //剩余红包

        int sum = 0;
        int rob;
        for (int i = 0; i < count;) {
            if (count == 1){//最后一个红包
                rob = score;
            }else {
                //+1 避免出现抢到0的情况 随机数[0,1)  在(score/count*2-1)减1操作，避免最后一个红包为0的情况
                rob = (int)(Math.random()*(score/count*2-1))+1;
            }
            System.out.println("第"+count+"个人抢到的红包："+rob);

            score-=rob;
            count--;
            sum+=rob;
        }
        System.out.println(sum);
    }
}
