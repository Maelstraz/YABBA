package com.latmod.yabba.net;

import com.feed_the_beast.ftbl.lib.net.LMNetworkWrapper;
import com.feed_the_beast.ftbl.lib.net.MessageToServer;
import com.latmod.yabba.YabbaRegistry;
import com.latmod.yabba.item.ItemHammer;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.network.ByteBufUtils;

/**
 * Created by LatvianModder on 15.12.2016.
 */
public class MessageSelectModel extends MessageToServer<MessageSelectModel>
{
    private String modelId;

    public MessageSelectModel()
    {
    }

    public MessageSelectModel(String id)
    {
        modelId = id;
    }

    @Override
    public LMNetworkWrapper getWrapper()
    {
        return YabbaNetHandler.NET;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        modelId = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeUTF8String(buf, modelId);
    }

    @Override
    public void onMessage(MessageSelectModel message, EntityPlayer player)
    {
        ItemStack stack = player.getHeldItem(EnumHand.MAIN_HAND);

        if(stack != null && stack.getItem() instanceof ItemHammer)
        {
            ItemHammer.setModel(stack, YabbaRegistry.INSTANCE.getModelId(message.modelId));
        }
    }
}