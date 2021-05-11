package de.wt4b.devtreffbot.reaction;

public enum ReactionRole {

    JAVA(841769889462616084L),
    KOTLIN(841769912383832095L),
    WEB(841770189971783680L),
    CSHARP(841769961412100157L),
    PYTHON(841770204710436865L),
    MINECRAFT(841770436076240906L),
    GITHUB(841770457497862196L);

    private final long roleID;

    ReactionRole(long roleID){
        this.roleID = roleID;
    }

    public long getRoleID() {
        return roleID;
    }
}
