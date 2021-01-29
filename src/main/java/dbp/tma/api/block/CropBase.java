package dbp.tma.api.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

public class CropBase extends BlockCrops {
    protected Item item;
    protected Item seed;
    protected int itemMeta;
    protected int seedMeta;
    protected String modid = "rematerialized";
    protected String name;
    protected int iconLength = 4;
    @SideOnly(Side.CLIENT)
    protected  IIcon[] cropTexture;

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int unused, int growth) {
        if (growth > 2) {
            return this.cropTexture[growth - 2];
        }
        return this.cropTexture[0];
    }

    protected ItemStack seedDrop() {
        return new ItemStack(seed, 1, seedMeta);
    }

    protected ItemStack itemDrop() {
        return new ItemStack(item, 1, itemMeta);
    }

    protected Item func_149866_i() {
        return seed;
    }

    protected Item func_149865_P() {
        return item;
    }

    public CropBase setItem(Item item) {
        this.item = item;
        return this;
    }

    public CropBase setSeed(Item seed) {
        this.seed = seed;
        return this;
    }

    public CropBase setItemMeta(int itemMeta) {
        this.itemMeta = itemMeta;
        return this;
    }

    public CropBase setSeedMeta(int seedMeta) {
        this.seedMeta = seedMeta;
        return this;
    }

    public CropBase setIconLength(int iconLength) {
        this.iconLength = iconLength;
        return this;
    }

    public CropBase setName(String name) {
        this.name = name;
        return this;
    }

    public CropBase setModid(String modid) {
        this.modid = modid;
        return this;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon) {
        this.cropTexture = new IIcon[iconLength];

        for (int i = 0; i < this.cropTexture.length; ++i) {
            this.cropTexture[i] = icon.registerIcon(modid + ":crop_" + name + "_" + i);
        }
    }

    public ItemStack getItemstackDropped(int meta, Random p_149650_2_, int p_149650_3_) {
        return meta == 7 ? this.itemDrop() : this.seedDrop();
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        int count = quantityDropped(metadata, fortune, world.rand);
        for (int i = 0; i < count; i++) {
            ItemStack item = getItemstackDropped(metadata, world.rand, fortune);
            if (item != null) {
                ret.add(item);
            }
        }

        if (metadata >= 7) {
            for (int i = 0; i < 3 + fortune; ++i) {
                if (world.rand.nextInt(15) <= metadata) {
                    ret.add(this.seedDrop());
                }
            }
        }
        return ret;
    }
}
