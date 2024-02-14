package DictionaryTypes.TrieStructureComponents;

import java.util.ArrayList;

public class States {

    private ArrayList<Edge> outgoingEdges = new ArrayList<>();
    private boolean accept;

    public States(int NN, boolean accept, String wordUpUntil) {

    }

    public void setEnd() {

    }

    public boolean isEnd() {
        return accept;
    }
}



