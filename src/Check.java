import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Check {

    private Set<String> allNames;

    public Check() throws IOException {
        this.allNames = new HashSet<>();
        InputStreamReader inputStreamReader =
            new InputStreamReader(new FileInputStream("allnames"), "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            allNames.add(line);
        }
    }

    public List<String> check() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        BufferedReader bufferedReader =
            new BufferedReader(new InputStreamReader(runtime.exec("cmd /c dir").getInputStream(), "gbk"));
        String line = null;
        Set<String> committed = new HashSet<>();
        Pattern pattern = Pattern.compile(".*_(1.*)\\..*");
        while ((line = bufferedReader.readLine()) != null) {
            if (line.startsWith("2020")) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    committed.add(matcher.group(1));
                }
            }
        }
        List<String> uncommitted = new ArrayList<>();
        for (String s : this.allNames) {
            if (!committed.contains(s)) {
                uncommitted.add(s);
            }
        }
        return uncommitted;
    }
}
