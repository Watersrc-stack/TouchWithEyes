package WatersrcStack.commands;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractAsyncCommand;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

public class EditConfigCommand extends AbstractAsyncCommand {

    public EditConfigCommand() {
        super("/tweeditconfig", "Opens menu to interactively edit the config of the plugin.");
    }

    @Override
    protected CompletableFuture<Void> executeAsync(@Nonnull CommandContext context) {
        context.sendMessage(Message.raw("A menu opens"));
        return CompletableFuture.completedFuture(null);
    }
}
