package de.tudresden.inf.st.mathgrass.graphs;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
public class GraphProvider {
    private final List<String> serializedGraphs;
    private final Random random = new Random();

    public GraphProvider() throws IOException {
        this.serializedGraphs = Collections.unmodifiableList(parseStringsFromResources());
    }

    public String getRandomGraph(){
        return serializedGraphs.get(random.nextInt(serializedGraphs.size()));
    }

    public List<String> parseStringsFromResources() throws IOException {
        List<String> result = new ArrayList<>();

        InputStream inputStream = this.getClass().getResourceAsStream("/graphs/graphs.json");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null && !line.equals("")) {
                result.add(line);
            }
        }
        return result;
    }

}
