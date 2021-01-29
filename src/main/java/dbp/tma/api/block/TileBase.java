package dbp.tma.api.block;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityChest;

public class TileBase extends TileEntityChest {
    private String name;
    private String baseName;
    private ItemStack[] contents = new ItemStack[256];
    private int size = 27;

    public TileBase setSize(int size) {
        this.size = size;
        return this;
    }

    @Override
    public int getSizeInventory() {
        return size;
    }

    @Override
    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.name : baseName;
    }

    @Override
    public void func_145976_a(String name) {
        this.name = name;
    }

    public TileBase setName(String name) {
        this.baseName = name;
        return this;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return this.name != null && this.name.length() > 0;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        NBTTagList nbttaglist = nbt.getTagList("Items", 10);
        this.contents = new ItemStack[this.getSizeInventory()];
        if (nbt.hasKey("CustomName", 8)) {
            this.name = nbt.getString("CustomName");
        }

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;
            if (j >= 0 && j < this.contents.length) {
                this.contents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.contents.length; ++i) {
            if (this.contents[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                this.contents[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbt.setTag("Items", nbttaglist);
        if (this.hasCustomInventoryName()) {
            nbt.setString("CustomName", this.name);
        }
    }

}
