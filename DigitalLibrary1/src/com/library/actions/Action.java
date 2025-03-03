package com.library.actions;

public class Action {
    private int actionId;
    private int viewerId;
    private String actionType;
    private String actionTimestamp;

    public Action(int actionId, int viewerId, String actionType, String actionTimestamp) {
        this.actionId = actionId;
        this.viewerId = viewerId;
        this.actionType = actionType;
        this.actionTimestamp = actionTimestamp;
    }

    // Getters and Setters
    public int getActionId() { return actionId; }
    public void setActionId(int actionId) { this.actionId = actionId; }
    public int getViewerId() { return viewerId; }
    public void setViewerId(int viewerId) { this.viewerId = viewerId; }
    public String getActionType() { return actionType; }
    public void setActionType(String actionType) { this.actionType = actionType; }
    public String getActionTimestamp() { return actionTimestamp; }
    public void setActionTimestamp(String actionTimestamp) { this.actionTimestamp = actionTimestamp; }
}
