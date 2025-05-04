package com.lynxxdg.fishnshits;
import com.lynxxdg.fishnshits.entities.CustomBobberEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.util.Arm;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

@Environment(EnvType.CLIENT)
public class CustomBobberEntityRenderer extends EntityRenderer<CustomBobberEntity, CustomBobberEntityState> {
    private static final Identifier TEXTURE = Identifier.ofVanilla("textures/entity/fishing_hook.png");
    private static final RenderLayer LAYER;

    public CustomBobberEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public void render(CustomBobberEntityState customBobberEntityState, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.push();
        matrixStack.scale(0.5F, 0.5F, 0.5F);
        matrixStack.multiply(this.dispatcher.getRotation());
        MatrixStack.Entry entry = matrixStack.peek();
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(LAYER);
        vertex(vertexConsumer, entry, i, 0.0F, 0, 0, 1);
        vertex(vertexConsumer, entry, i, 1.0F, 0, 1, 1);
        vertex(vertexConsumer, entry, i, 1.0F, 1, 1, 0);
        vertex(vertexConsumer, entry, i, 0.0F, 1, 0, 0);
        matrixStack.pop();
        float f = (float)customBobberEntityState.pos.x;
        float g = (float)customBobberEntityState.pos.y;
        float h = (float)customBobberEntityState.pos.z;
        VertexConsumer vertexConsumer2 = vertexConsumerProvider.getBuffer(RenderLayer.getLineStrip());
        MatrixStack.Entry entry2 = matrixStack.peek();
        int j = 16;

        for(int k = 0; k <= 16; ++k) {
            renderFishingLine(f, g, h, vertexConsumer2, entry2, percentage(k, 16), percentage(k + 1, 16), customBobberEntityState.color);
        }

        matrixStack.pop();
        super.render(customBobberEntityState, matrixStack, vertexConsumerProvider, i);
    }

    public static Arm getArmHoldingRod(PlayerEntity player) {
        return player.getMainHandStack().getItem() instanceof FishingRodItem ? player.getMainArm() : player.getMainArm().getOpposite();
    }


    private Vec3d getHandPos(PlayerEntity player, float f, float tickProgress) {
        int i = getArmHoldingRod(player) == Arm.RIGHT ? 1 : -1;
        if (this.dispatcher.gameOptions.getPerspective().isFirstPerson() && player == MinecraftClient.getInstance().player) {
            double m = (double)960.0F / (double)(Integer)this.dispatcher.gameOptions.getFov().getValue();
            Vec3d vec3d = this.dispatcher.camera.getProjection().getPosition((float)i * 0.525F, -0.1F).multiply(m).rotateY(f * 0.5F).rotateX(-f * 0.7F);
            return player.getCameraPosVec(tickProgress).add(vec3d);
        } else {
            float g = MathHelper.lerp(tickProgress, player.lastBodyYaw, player.bodyYaw) * ((float)Math.PI / 180F);
            double d = (double)MathHelper.sin(g);
            double e = (double)MathHelper.cos(g);
            float h = player.getScale();
            double j = (double)i * 0.35 * (double)h;
            double k = 0.8 * (double)h;
            float l = player.isInSneakingPose() ? -0.1875F : 0.0F;
            return player.getCameraPosVec(tickProgress).add(-e * j - d * k, (double)l - 0.45 * (double)h, -d * j + e * k);
        }
    }

    private static float percentage(int value, int denominator) {
        return (float)value / (float)denominator;
    }

    private static void vertex(VertexConsumer buffer, MatrixStack.Entry matrix, int light, float x, int y, int u, int v) {
        buffer.vertex(matrix, x - 0.5F, (float)y - 0.5F, 0.0F).color(-1).texture((float)u, (float)v).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(matrix, 0.0F, 1.0F, 0.0F);
    }

    private static void renderFishingLine(float x, float y, float z, VertexConsumer buffer, MatrixStack.Entry matrices, float segmentStart, float segmentEnd, int color) {
        float f = x * segmentStart;
        float g = y * (segmentStart * segmentStart + segmentStart) * 0.5F + 0.25F;
        float h = z * segmentStart;
        float i = x * segmentEnd - f;
        float j = y * (segmentEnd * segmentEnd + segmentEnd) * 0.5F + 0.25F - g;
        float k = z * segmentEnd - h;
        float l = MathHelper.sqrt(i * i + j * j + k * k);
        i /= l;
        j /= l;
        k /= l;
        buffer.vertex(matrices, f, g, h).color(color).normal(matrices, i, j, k);
    }

    @Override
    public CustomBobberEntityState createRenderState() {
        return new CustomBobberEntityState();
    }

    private static int getColor(CustomBobberEntity customBobberEntity) {
        PlayerEntity player = customBobberEntity.getPlayerOwner();
        if (player != null) {
            int color = player.getMainHandStack().getOrDefault(ModComponents.BOBBER_COLOR, 0);
            if (color == 0)
                color = player.getOffHandStack().getOrDefault(ModComponents.BOBBER_COLOR, 0);
            if (color == 0)
                color = Colors.BLACK;
            return color;
        }
        return Colors.BLACK;
    }

    @Override
    public void updateRenderState(CustomBobberEntity customBobberEntity, CustomBobberEntityState customBobberEntityState, float f) {
        super.updateRenderState(customBobberEntity, customBobberEntityState, f);
        PlayerEntity playerEntity = customBobberEntity.getPlayerOwner();
        customBobberEntityState.color = getColor(customBobberEntity);
        if (playerEntity == null) {
            customBobberEntityState.pos = Vec3d.ZERO;
        } else {
            float g = playerEntity.getHandSwingProgress(f);
            float h = MathHelper.sin(MathHelper.sqrt(g) * (float)Math.PI);
            Vec3d vec3d = this.getHandPos(playerEntity, h, f);
            Vec3d vec3d2 = customBobberEntity.getLerpedPos(f).add((double)0.0F, (double)0.25F, (double)0.0F);
            customBobberEntityState.pos = vec3d.subtract(vec3d2);
        }
    }

    static {
        LAYER = RenderLayer.getEntityCutout(TEXTURE);
    }
}
