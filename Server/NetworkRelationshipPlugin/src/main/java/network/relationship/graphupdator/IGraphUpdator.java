package network.relationship.graphupdator;

import network.relationship.graphupdator.ApplicationGraphUpdator.ApplicationRelatedInfo;

public interface IGraphUpdator {
    public long update(ApplicationRelatedInfo appDirectGraph);
}
