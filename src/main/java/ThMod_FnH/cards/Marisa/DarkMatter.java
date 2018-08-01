package ThMod_FnH.cards.Marisa;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomCard;
import ThMod_FnH.patches.AbstractCardEnum;

public class DarkMatter extends CustomCard {
	public static final String ID = "DarkMatter";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Defend.png";
	private static final int COST = 0;
	private static final int BLC_GAIN = 4;
	private static final int UPG_BLC = 2;

	public DarkMatter() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.SELF);
		this.isEthereal =true;
		this.block = this.baseBlock = BLC_GAIN;
	}
	
	public void triggerOnExhaust() {
		AbstractPlayer p = AbstractDungeon.player;
		AbstractDungeon.actionManager.addToBottom(
				new GainBlockAction(p, p, this.block));
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		
		AbstractCard c = new DarkMatter();
		if (this.upgraded)
			c.upgrade();
		AbstractDungeon.actionManager.addToBottom(
				new MakeTempCardInDrawPileAction(c, 2, true, true));
		
		p.drawPile.shuffle();
	    for (AbstractRelic r : p.relics)
	        r.onShuffle();
	}

	public AbstractCard makeCopy() {
		return new DarkMatter();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPG_BLC);
			this.rawDescription = DESCRIPTION_UPG;
			initializeDescription();
		}
	}
}