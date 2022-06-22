package de.tudresden.inf.st.mathgrass.minimalprototype;

import de.tudresden.inf.st.mathgrass.graphs.GraphProvider;
import de.tudresden.inf.st.mathgrass.tasks.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class PrototypeEndpoints {
    GraphProvider graphProvider;

    public PrototypeEndpoints(GraphProvider graphProvider) {
        this.graphProvider = graphProvider;
    }

    @GetMapping("/availableTaskTypes")
    @ResponseBody
    public List<TaskType> getAvailableTaskPackages(){
        try {
            graphProvider.parseStringsFromResources();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return List.of(new TaskType("planarity", "Planar Graphs"), new TaskType("bipartite", "Bipartite Graphs"));
    }

    @GetMapping("/getRandomGraph")
    @ResponseBody
    public String getRandomGraph(){
        return graphProvider.getRandomGraph();
    }

}
