package com.company.linking_tree;

import com.company.linking_tree.example.Arrangement;
import com.company.linking_tree.example.CobRequest;
import com.company.linking_tree.example.Party;

import java.util.List;

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

        lTree.add("cob2", CobRequest.class, "arr2", new Arrangement("arr2", "arrangement value 2"));

        lTree.findNode( "party1", Party.class).value = new Party ("party1", "party value 1");

        System.out.println(lTree.print());
        //Erases child references
        lTree.add("cob1", new CobRequest("cob1", "cob value 1"));


        System.out.println(lTree.findAllFrom("cob1", CobRequest.class, Party.class));
        System.out.println(lTree.findAllFrom("cob2", CobRequest.class, Party.class));
        System.out.println();
        System.out.println(lTree.findAllFrom("cob1", CobRequest.class, Arrangement.class));
        System.out.println(lTree.findAllFrom("cob2", CobRequest.class, Arrangement.class));
        System.out.println();
        System.out.println(lTree.findAll(CobRequest.class));


        List<CobRequest> cobRequests = lTree.findAll(CobRequest.class);
        cobRequests.forEach(cobRequest -> {
            cobRequest.parties.addAll(
                    lTree.findAllFrom(cobRequest.id, cobRequest.getClass(), Party.class));
            cobRequest.arrangements.addAll(
                    lTree.findAllFrom(cobRequest.id, cobRequest.getClass(), Arrangement.class));
        });

        System.out.println(cobRequests);
    }
}
