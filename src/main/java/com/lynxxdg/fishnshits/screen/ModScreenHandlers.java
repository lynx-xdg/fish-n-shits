package com.lynxxdg.fishnshits.screen;

import com.lynxxdg.fishnshits.Fishnshits;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {
    public static final ScreenHandlerType<TackleboxScreenHandler> TACKLEBOX_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(Fishnshits.MOD_ID, "tacklebox_screen_handler"),
                    new ExtendedScreenHandlerType<>(TackleboxScreenHandler::new, BlockPos.PACKET_CODEC));


    public static void initialize() {
        Fishnshits.LOGGER.info("Registering Screen Handlers for " + Fishnshits.MOD_ID);
    }
}
