import java.util.ArrayList;
import java.util.List;

/**
 * @author xiongyongshun
 * VM Args: java -Xms10m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError
 */
public class OutOfMemoryErrorTest {


    public static void main(String[] args) {
        List<AbcBean> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(new AbcBean(i++));
        }
    }
}
