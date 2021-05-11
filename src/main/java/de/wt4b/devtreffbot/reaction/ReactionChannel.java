package de.wt4b.devtreffbot.reaction;

public enum ReactionChannel {

    SUGGESTIONS(840881135280979998L),
    ROLES(841769291036229652L);

    private final long channelID;

    ReactionChannel(long channelID){
        this.channelID = channelID;
    }

    public long getChannelID() {
        return channelID;
    }
}
