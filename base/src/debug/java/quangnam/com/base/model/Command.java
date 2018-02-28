package quangnam.com.base.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by co-well on 2/28/18.
 */

public class Command {

    public String name;
    public CommandExecutable executable;
    public String helper;

    public interface CommandExecutable {
        void execute(List<String> params);
    }

    public void execute(String cmd) {
        ArrayList<String> params = new ArrayList<>(Arrays.asList(cmd.split(" ")));
        params.remove(0);

        executable.execute(params);
    }
}
