package engine.main;

import catfish.base.queue.QueueMessager;

public interface RuleEngineWorker {

	public QueueMessager execute(QueueMessager messager, Integer channel);
}
