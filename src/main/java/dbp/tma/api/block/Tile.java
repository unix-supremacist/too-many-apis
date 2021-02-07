package dbp.tma.api.block;

import net.minecraft.block.BlockFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class Tile extends TileEntity implements ISidedInventory {
	protected int power;
	protected int processTime;
	protected String name;
	protected ItemStack[] inventory;
	private static final int[] slotsTop = new int[] {0};
	private static final int[] slotsBottom = new int[] {2, 1};
	private static final int[] slotsSides = new int[] {1};

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return side == 0 ? slotsBottom : (side == 1 ? slotsTop : slotsSides);
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack item, int side) {
		return this.isItemValidForSlot(slot, item);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack item, int side) {
		return true;
	}

	@Override
	public int getSizeInventory() {
		return this.inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int count) {
		ItemStack i = null;
		if (this.inventory[slot] != null){
			if (this.inventory[slot].stackSize <= count){
				i = this.inventory[slot];
				this.inventory[slot] = null;
			}else{
				i = this.inventory[slot].splitStack(count);
				if (this.inventory[slot].stackSize == 0){
					this.inventory[slot] = null;
				}
			}
		}
		return i;
	}

	//Unneeded for machines, could be useful on destruction
	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack i = null;
		if (this.inventory[slot] != null){
			i = this.inventory[slot];
			this.inventory[slot] = null;
		}
		return i;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack item) {
		this.inventory[slot] = item;
		if (item != null && item.stackSize > this.getInventoryStackLimit()){
			item.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.name : "container";
	}

	public void setInventoryName(String name){
		this.name = name;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return this.name != null && this.name.length() > 0;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this
			?
			false :
			player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	@Override public void openInventory() {}
	@Override public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack item) {
		return true;
	}

	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		NBTTagList list = tag.getTagList("Items", 10);
		this.inventory = new ItemStack[this.getSizeInventory()];

		for(int i = 0; i < list.tagCount(); i++){
			NBTTagCompound tag2 = list.getCompoundTagAt(i);
			byte b = tag2.getByte("Slot");
			if(b > 0 && b < this.inventory.length) this.inventory[b] = ItemStack.loadItemStackFromNBT(tag2);
		}

		this.power = tag.getShort("Power");
		if(tag.hasKey("CustomName", 8)) this.name = tag.getString("CustomName");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("Power", this.power);
		NBTTagList list = new NBTTagList();

		for(int i = 0; i < this.inventory.length; i++){
			if (this.inventory[i] != null) {
				NBTTagCompound tag2 = new NBTTagCompound();
				tag2.setByte("Slot", (byte) i);
				this.inventory[i].writeToNBT(tag2);
				list.appendTag(tag2);
			}
		}

		tag.setTag("Items", list);
		if (this.hasCustomInventoryName()) tag.setString("CustomName", this.name);
	}

	private boolean canProcess(){
		boolean canProcess = false;
		if (this.inventory[0] != null){
			canProcess = true;
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[0]);
			if (itemstack == null) canProcess = false;
			if (this.inventory[2] != null) {
				if (!this.inventory[2].isItemEqual(itemstack)) canProcess = false;
				int result = inventory[2].stackSize + itemstack.stackSize;
				canProcess = result <= getInventoryStackLimit() && result <= this.inventory[2].getMaxStackSize(); //Forge BugFix: Make it respect stack sizes properly.
			}
		}
		return canProcess;
	}

	public void processItem(){
		if(this.canProcess()){
			ItemStack i = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[0]);
			if (this.inventory[2] == null){
				this.inventory[2] = i.copy();
			}else if(this.inventory[2].getItem() == i.getItem()){
				this.inventory[2].stackSize += i.stackSize;
			}

			this.inventory[0].stackSize--;

			if(this.inventory[0].stackSize < 0){
				this.inventory[0] = null;
			}
		}
	}

	@Override
	public void updateEntity(){
		boolean flag = this.power > 0;
		boolean flag1 = false;

		if (this.power > 0) this.power--;

		if (!this.worldObj.isRemote){
			if (this.power != 0 || this.inventory[1] != null && this.inventory[0] != null){
				if (this.power == 0 && this.canProcess()){
					if (this.power > 0){
						flag1 = true;

						if (this.inventory[1] != null){
							this.inventory[1].stackSize--;
							if (this.inventory[1].stackSize == 0)
								this.inventory[1] = inventory[1].getItem().getContainerItem(inventory[1]);
						}
					}
				}

				if (this.power> 0 && this.canProcess()){
					this.processTime++;

					if (this.processTime == 200){
						this.processTime = 0;
						this.processItem();
						flag1 = true;
					}
				}else{
					this.processTime = 0;
				}
			}

			if (flag != this.power > 0){
				flag1 = true;
				BlockFurnace.updateFurnaceBlockState(this.power > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}

		if (flag1){
			this.markDirty();
		}
	}
}