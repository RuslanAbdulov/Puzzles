package com.company.linking_tree;

import com.company.linking_tree.example.Arrangement;
import com.company.linking_tree.example.CobRequest;
import com.company.linking_tree.example.Party;
/**
 * TODO consider different structure - multi-dimensional table of references and a collection of Map<Ref, Entity>
 */

public class LinkingTreeMain {

    public static void main(String[] args) {
        LinkingTree lTree = new LinkingTree()
                .addLevel(String.class, CobRequest.class)
                .addLevel(String.class, Arrangement.class)
                .addLevel(String.class, Party.class);


        lTree.add("cob1", CobRequest.class);
        lTree.add("cob2", new CobRequest("cob2", "cob value 2"));

        lTree.add("cob1", CobRequest.class, "arr1", new Arrangement("arr1", "arrangement value 1"));
        lTree.add("arr1", Arrangement.class, "party1", Party.class);

        lTree.findNode( "party1", Party.class).value = new Party ("party1", "party value 1");

        System.out.println(lTree.print());


//        lTree.findAll(CobRequest.class).forEach(
//                cobRequest -> cobRequest.parties.add()
//        );
    }
}
