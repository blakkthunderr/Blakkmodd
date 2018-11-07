package examplemod.monsters;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import examplemod.cards.Rust;
import examplemod.powers.CrumblingPower;

public class StoneGiant extends AbstractMonster
{
    public static final String ID = "StoneGiant";
    private static final MonsterStrings monsterStrings;
    public static final String NAME;
    public static final String[] MOVES;
    public static final String[] DIALOG;
    private static final int HP = 1000;
    private static final int A_HP = 1100;
    private static final int BASH_DMG = 30;
    private static final int RUSH_DMG = 100;
    private static final int A_BASH_DMG = 40;
    private static final int A_RUSH_DMG = 120;
    private static final int DEBUFF_AMT = 2;
    private static final int METAL_AMT = 7;
    private static final int A_METAL_AMT = 10;
    private static final int THORNS_AMT = 1;
    private static final int A_THORNS_AMT = 2;
    private int bashDmg;
    private int rushDmg;
    private int metalAmt;
    private int thornsAmt;
    private int applyFrail;
    private static final byte STUN1 = 1;
    private static final byte GIANTRAGE = 2;
    private static final byte EARTHQUAKE = 3;
    private static final byte STUN2 = 4;
    private static final byte GIANTCRUSH = 5;

    private static final int ANGRY_LEVEL = 3;
    private boolean usedBellow;

    public StoneGiant() {
        super(StoneGiant.NAME, "StoneGiant", 1000, -80.0f, -10.0f, 270.0f, 380.0f, "img/StoneGiant.png");
        this.intentOffsetX = -1.0f * Settings.scale;
        this.type = EnemyType.BOSS;
        this.dialogX = -60.0f * Settings.scale;
        this.dialogY = 50.0f * Settings.scale;
        if (AbstractDungeon.ascensionLevel >= 9) {
            this.setHp(StoneGiant.A_HP);
        }
        else {
            this.setHp(StoneGiant.HP);
        }
        if (AbstractDungeon.ascensionLevel >= 4) {
            this.bashDmg = A_BASH_DMG;
            this.rushDmg = A_RUSH_DMG;
        }
        else {
            this.bashDmg = BASH_DMG;
            this.rushDmg = RUSH_DMG;
        }

        if (AbstractDungeon.ascensionLevel >= 19) {
            this.metalAmt = A_METAL_AMT;
            this.thornsAmt = A_THORNS_AMT;
            this.applyFrail = 100;

        }
        else {
            this.metalAmt = METAL_AMT;
            this.thornsAmt = THORNS_AMT;
            this.applyFrail = 75;

        }
        this.damage.add(new DamageInfo(this, this.bashDmg));
        this.damage.add(new DamageInfo(this, this.rushDmg));
        //this.loadAnimation("images/monsters/fadingForest/nobGremlin/skeleton.atlas", "images/monsters/fadingForest/nobGremlin/skeleton.json", 1.0f);
       // final AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
        //e.setTime(e.getEndTime() * MathUtils.random());
    }

    @Override
    public void usePreBattleAction() {
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.getCurrRoom().playBgmInstantly("BOSS_BEYOND");
        UnlockTracker.markBossAsSeen("GIANT");
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new SlowPower(this, 1), 1));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new CrumblingPower(this, 1), 1));

    }

    @Override
    public void takeTurn() {
        switch (this.nextMove) {
            case STUN1: {

                AbstractDungeon.actionManager.addToBottom(new TalkAction(this, StoneGiant.DIALOG[0], 1.0f, 3.0f));

                this.setMove(StoneGiant.MOVES[2], (byte)3, Intent.ATTACK_DEBUFF, this.bashDmg, 2, true);
                break;
            }
            case GIANTRAGE: {

                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new StrengthPower(AbstractDungeon.player, applyFrail), applyFrail));
                applyFrail += 50;
                this.setMove(StoneGiant.MOVES[4], (byte)5, Intent.ATTACK, this.rushDmg, 1, false);
                break;
            }
            case EARTHQUAKE: {
                for(int i = 0; i < 2; ++i) {
                    AbstractDungeon.actionManager.addToBottom(new AnimateSlowAttackAction(this));
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Dazed(), 1));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new WeakPower(AbstractDungeon.player, 1, true), 1));


                }
                this.setMove(StoneGiant.MOVES[1], (byte)2, Intent.BUFF);
                break;

            }
            case STUN2: {
                this.setMove(StoneGiant.MOVES[1], (byte)2, Intent.BUFF, this.bashDmg, 1, false);
                break;

            }
            case GIANTCRUSH: {
                AbstractDungeon.actionManager.addToBottom(new AnimateSlowAttackAction(this));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.BLUNT_HEAVY));

                this.setMove(StoneGiant.MOVES[0], (byte)1, Intent.STUN);
                break;

            }
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    @Override
    public void die() {
        this.useFastShakeAnimation(5.0F);
        CardCrawlGame.screenShake.rumble(4.0F);
        ++this.deathTimer;
        super.die();
        this.onBossVictoryLogic();

    }


    @Override
    protected void getMove(final int num) {
        if (!this.usedBellow) {
            this.usedBellow = true;
            this.setMove((byte)1, Intent.STUN);
            return;

        }/*
        if (num < 33) {
            this.setMove(TwilightGuardian.MOVES[0], (byte)2, Intent.ATTACK_DEBUFF, this.damage.get(1).base);
            return;
        }
        if (this.lastTwoMoves((byte)1)) {
            this.setMove(TwilightGuardian.MOVES[0], (byte)2, Intent.ATTACK_DEBUFF, this.damage.get(1).base);
        }
        else {
            this.setMove((byte)1, Intent.ATTACK, this.damage.get(0).base);
        }*/
    }
    /*
    @Override
    public void die() {
        super.die();
        AbstractDungeon.scene.fadeInAmbiance();
        CardCrawlGame.music.fadeOutTempBGM();
    }
    */
    static {
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("StoneGiant");
        NAME = StoneGiant.monsterStrings.NAME;
        MOVES = StoneGiant.monsterStrings.MOVES;
        DIALOG = StoneGiant.monsterStrings.DIALOG;
    }
}
