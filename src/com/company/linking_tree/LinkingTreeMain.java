package com.company.linking_tree;

import com.company.linking_tree.example.Arrangement;
import com.company.linking_tree.example.CobRequest;
import com.company.linking_tree.example.Party;
/**
 * TODO consider different structure - multi-dimensional table of references and a collection of Map<Ref, Entity>
 */

public class LinkingTreeMain {

    public static void main(String[] args) {
        LinkingTree lTree = new LinkingTree();
        lTree.addLevel(String.class, CobRequest.class);
        lTree.addLevel(String.class, Arrangement.class);
        lTree.addLevel(String.class, Party.class);

        System.out.println(lTree.print());
    }
}
