package io.github.lexadiky.uzi.agent.processor

import io.github.lexadiky.uzi.engine.task.UziTask
import io.netty.channel.ChannelFactory
import kotlinx.coroutines.channels.Channel

class SessionProcessor {
    private val taskChannel: Channel<UziTask> = Channel(capacity = Channel.UNLIMITED)

    suspend fun enqueue(task: UziTask) {
        taskChannel.send(task)
    }

    suspend fun startProcessing() {
        for (task in taskChannel) {
            // TODO run task
        }
    }
}