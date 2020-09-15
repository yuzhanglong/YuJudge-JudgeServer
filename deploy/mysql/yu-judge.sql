/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : yu-judge

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 11/09/2020 18:45:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE `yu-judge`;

USE `yu-judge`;

-- ----------------------------
-- Table structure for daily_word
-- ----------------------------
DROP TABLE IF EXISTS `daily_word`;
CREATE TABLE `daily_word`
(
    `id`          int(0) UNSIGNED                                               NOT NULL AUTO_INCREMENT,
    `title`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `content`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `create_time` datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0),
    `delete_time` datetime(0)                                                   NULL DEFAULT NULL,
    `update_time` datetime(0)                                                   NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 401
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of daily_word
-- ----------------------------
INSERT INTO `daily_word`
VALUES (1,
        'Human life,like flood water and do not hear of islands and reefs,it is difficult to create beautiful waves.',
        '人的生命，似洪水奔流，不遇着岛屿和暗礁，难以激起美丽的浪花。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (2, 'But all sunshine without shade, all pleasure without pain, is not life at all. ',
        '但只有阳光没有阴影，只有快乐没有痛苦，根本不是真正的生活。 ', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (3, 'Don\'t know if we each have a destiny, or if we’re all just floating around accidental—like on a breeze.',
        '我不懂我们是否有着各自的命运，还是只是到处随风飘荡。 ', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (4, 'Sadness is easier because its surrender.', '悲伤很容易，因为那是投降。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (5, 'The cloud stood humbly in a corner of the sky. The morning crowned it with splendor.',
        '白云谦卑地站在天边，晨光给它披上壮丽的光彩。--泰戈尔', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (6, 'The more we do, the more we can do. The busier we are, the more leisure we have.', '事越做越会做，人越忙越有空。', NULL,
        NULL, NULL);
INSERT INTO `daily_word`
VALUES (7,
        'Baby you\'ve done enough that cut your breath.Don\'t beat yourself up don\'t need to turn so fast.Sometimes we come last but we did our best.',
        '你已经受够了白费力气，请不要自暴自弃，也不必太快抽离，有时我们终能实现梦想只要我们竭尽全力。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (8, ' We must hang together, or we\'ll be hanged separately.', '我们必须团结在一起，否则我们将被一个个绞死。 ', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (10, 'Sadness is easier because its surrender.', '悲伤很容易，因为那是投降。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (11, 'Don\'t know if we each have a destiny, or if we’re all just floating around accidental—like on a breeze.',
        '我不懂我们是否有着各自的命运，还是只是到处随风飘荡。 ', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (12,
        'Human life,like flood water and do not hear of islands and reefs,it is difficult to create beautiful waves.',
        '人的生命，似洪水奔流，不遇着岛屿和暗礁，难以激起美丽的浪花。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (13, 'The more we do, the more we can do. The busier we are, the more leisure we have.', '事越做越会做，人越忙越有空。', NULL,
        NULL, NULL);
INSERT INTO `daily_word`
VALUES (14, ' We must hang together, or we\'ll be hanged separately.', '我们必须团结在一起，否则我们将被一个个绞死。 ', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (15, 'But all sunshine without shade, all pleasure without pain, is not life at all. ',
        '但只有阳光没有阴影，只有快乐没有痛苦，根本不是真正的生活。 ', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (16,
        'Baby you\'ve done enough that cut your breath.Don\'t beat yourself up don\'t need to turn so fast.Sometimes we come last but we did our best.',
        '你已经受够了白费力气，请不要自暴自弃，也不必太快抽离，有时我们终能实现梦想只要我们竭尽全力。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (17, 'The cloud stood humbly in a corner of the sky. The morning crowned it with splendor.',
        '白云谦卑地站在天边，晨光给它披上壮丽的光彩。--泰戈尔', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (18, 'Never trouble trouble till trouble troubles you.', '麻烦没来找你，就别去自找麻烦。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (19,
        'All my life, I\'ve only been pretending. Without me, his world will go on turning. A world that\'s full of happiness that I have never known.',
        '这一生不过是自欺欺人罢了。没有我他的世界依然如故，那里满是我从未体会过的幸福。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (20, 'You don\'t become what you want, you become what you believe. ', '不是成为你想要的，而是成为你所相信的。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (21, 'Is life always this hard,or is it just when you are a kid?Always like this. ',
        '人生总是那么艰难吗？还是只有孩童时这样？  总是如此。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (22,
        '“You know, I\'m really glad you decided to learn Mandarin.” “Why?”   “Once you\'re fluent, you\'ll have a billion more people to annoy instead of me.”',
        '“话说，你终于要学普通话了我还真高兴。”  “为嘛？” “等你说顺溜了，有十多亿中国人民等着你去烦，你就不用来烦我了。”  ', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (23,
        'I figure life is a gift and I don\'t intend on wasting it. You never know what hand you\'re going to get dealt next. You learn to take life as it comes at you.',
        ' 我觉得生命是一份礼物,我不想浪费它,你不会知道下一手牌会是什么,要学会接受生活。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (24,
        'We all have moments of desperation. But if we can face them head on, that\'s when we find out just how strong we really are.',
        '我们都有绝望的时候，只有在勇敢面对时，我们才知道我们有多坚强。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (25, 'Progress is the activity of today and the assurance of tomorrow .', '进步是今天的活动、明天的保证。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (26, 'If they throw stones at you, don\'t throw back, use them to build your own foundation instead.',
        '如果别人朝你扔石头，就不要扔回去了，留着作你建高楼的基石。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (27, 'It’s better to be alone than to be with someone you’re not happy to be with.', '宁愿一个人呆着，也不要跟不合拍的人呆一块。',
        NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (28, 'Yesterday is history, tomorrow is a mystery, but today is a gift, that is why it’s called present！',
        '昨天已成为历史，明天是未知的，而今天是上天赐予我们的礼物，这就是为什么我们把它叫做现在！', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (29, 'Persistence is the common trait of anyone who has had a significant impact on the world.',
        '坚持是世界上每个有建树的人共有的品质。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (30,
        'A single hand that wipes tears during failures is much better than countless hands that come together to clap on success.  ',
        '失败时有人伸出一只手来为你擦泪，会好过成功时无数人伸手为你鼓掌。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (31, 'You can learn great things from your mistakes when you aren\'t busy denying them.',
        '当你不再急于否认错误时，你就学到了重要的一课。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (32, 'There are more things between heaven and earth than are dreamt of in your philosophy. 　',
        '在这天地间有许多事情是人类哲学所不能解释的。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (33,
        'Land? Land is a ship too big for me. It’s a woman too beautiful, it’s a voyage too long, a perfume too strong, it’s music I don’t know how to make.',
        '陆地？对我来说，陆地是艘太大的船，是位太美的美女。是条太长的航程，是瓶太香的香水，是篇无从弹奏的乐章。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (34,
        ' The only way to keep your health is to eat what you don\'t want, drink what you don\'t like, and do what you\'d rather not.-- Mark Twain',
        '保持身体健康的唯一办法，就是吃点你不想吃的，喝点你不想喝的，以及做点你不愿做的事情。——马克·吐温', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (35, 'I can accept defeat but could not accept to give up .', '我可以接受失败，但不能接受放弃.（迈克尔·乔丹座右铭）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (36, 'Behind the fear of an ideal you, you create the fear, you can beat him.', '恐惧的背后有一个理想的你，恐惧是你自己创造，你也可以打败他。',
        NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (37,
        'We learn to let go of things and people that hurt us in the past and just move on. For life is all about moving on.',
        '我们学着放开过去伤害我们的人和事，学着只向前看。因为生活本来就是一往直前的。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (38, 'Never regret anything because at one time it was exactly what you wanted. ', '不要后悔做任何事情，因为曾经有个时候，那正是你想要的。',
        NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (39,
        'A soldier would rather face a firing squad a thousand times over than just once have to look his own colleagues in the eye knowing he had failed them.',
        '一名军人宁愿被行刑队枪毙一千次，也不愿让战友看见自己逃命的背影。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (40,
        'Be confident with yourself and stop worrying what other people think. Do what\'s best for your future happiness.',
        '对自己要自信，别再管其它人怎么想了，为了未来幸福全力以赴吧！ ', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (41,
        'The greatest contest is always with the enemy within,and that battleground can be the loneliest place on earth.',
        '同自己内心的敌人斗争永远是最艰苦的，那片战场也是地球上最孤独的地方。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (42,
        'Whenever you feel like criticizing any one, just remember that all the people in this world haven’t had the advantages that you’ve had. ',
        '每当你觉得想要批评什么人的时候，你切要记着，这个世界上的人并非都具备你禀有的条件。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (43, 'Real dream is the other shore of reality.', '真正的梦就是现实的彼岸。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (44, 'Every man dies, not every man really lives.', '任何人都将会死去，然而并不是每个人都真真正正的活过。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (45,
        'Life\'s a little bit messy. We all make mistakes. No matter what type of animal you are, change starts with you.',
        '生活总会有点不顺意，我们都会犯错。天性如何并不重要，重要的是你开始改变。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (46, 'To be a person who knows how to be silent and always keep aspiring.', '做一个适时懂得缄默而始终持有骨架的人。', NULL, NULL,
        NULL);
INSERT INTO `daily_word`
VALUES (47, 'I am a slow walker,but I never walk backwards. ', '我走得很慢，但是我从来不会后退。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (48, 'I don\'t want to be the next Michael Jordan, I only want to be Kobe Bryant.', '我不想和别人一样，即便这个别人是乔丹。', NULL,
        NULL, NULL);
INSERT INTO `daily_word`
VALUES (49, 'I believe I am.Born as the bright summer flowers.Do not withered undefeated fiery demon rule. ',
        '我相信自己，生来如同璀璨的夏日之花。不凋不败，妖冶如火。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (50,
        'For I know that my Redeemer lives, and that at the last he will stand up on the earth; and after my skin has been thus destroyed, then in my flesh I shall see God. ',
        '我知道我的救赎主活着，末了必站在地上。我这皮肉灭绝之后，我必在肉体之外得见上帝。--（《旧约·约伯记》第19章） ', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (51, 'This moment nap, you will have a dream. But this moment study, you will interpret a dream.',
        '此刻打盹，你将做梦；而此刻学习，你将圆梦。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (52,
        'The most important thing in any relationship is not what you get but what you give. In any case, the giving of love is an education in itself.',
        '最重要的不是你得到什么而是给了什么。任何情况下，爱的付出本身就是一门学问。（Eleanor Roosevelt）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (53, 'Hard work never killed anybody. But why take the risk.', '努力工作不会导致死亡，不过我不会用自己去证明。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (54, 'The power of the wind is at your command.The balance of power must be preserved.', '风之化身听候您的差遣！力量的均衡必须维持！',
        NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (55,
        'I\'m just a little bit caught in the middle. Life is a maze and love is a riddle, I don\'t know where to go, can\'t do it alone.',
        '我有点儿左右两难，生活是座迷宫，爱是个谜，我不知道该去哪儿，我一个人做不到。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (56,
        'My flower is ephemeral, and she has only four thorns to defend herself against the world． And I have left on my planet, all alone！',
        '我的花生命是短暂的，她只有四根刺可以保护自己，抵御世界，我却将她独自留在我的星球上了！', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (57, 'People die, but books never die. No man and no force can abolish memory.',
        '人会死亡，书却无朽。没有任何人可以丢弃记忆。--富兰克林·罗斯福', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (58, 'Never let fear or shame keep you from celebrating the unique people that you are.',
        '绝不要让害怕或羞怯，阻止你欣赏自己的独一无二。（Kristen Anderson-Lopez）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (59, 'Only those who attempt the absurd can achieve the impossible.––Albert Einstein',
        '只有那些努力完成荒谬的事才有可能实现不可能。--爱因斯坦', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (60,
        'Make up your mind to act decidedly and take the consequences. No good is ever done in this world by hesitation.',
        '下定决心，果断行动，并承担后果。在这世界上犹豫不决成就不了任何事。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (61, 'Mama always said life was like a box of chocolates. You never know what you\'re gonna get.',
        '妈妈说生活就像一盒巧克力，你永远都不知道你会得到什么。（《阿甘正传》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (62, 'Money is not everything. There\'s MasterCard.', ' 钞票不是万能的, 有时还需要信用卡。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (63, 'He cheated death. No, he made a promise to death that he didn\'t keep.',
        '他欺骗了死神。不，应该说是他向死神做出了承诺，却没有遵守诺言。--电影《恐怖游轮》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (64, 'Fear of death is worse than death itself.', '畏惧死亡比死亡本身更糟糕。--《红与黑》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (65,
        'And when we come together, combine the light that shines within. There is nothing we can\'t do, there is no battle we can\'t win.',
        '当我们在一起时，光会更亮。没有什么是我们做不了的，没有我们赢不了的比赛。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (66, 'The most important quality for an investor is temperament, not intellect.', '投资人最重要的品质不是智慧，而是性情。--巴菲特',
        NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (67, 'A great many people think they are thinking when they are merely rearranging their prejudices.',
        '很多人觉得他们在思考，但实际上只是重新安排自己的偏见。（William James）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (68,
        'It is not in the still calm of life, or in the repose of a pacific station, that great characters are formed.',
        '伟大的人格，不会形成于平静的生活，或无风的太平洋港。（Abigail Adams）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (69, 'Mental attitude plays a far more important role in a person\'s success or failure than mental capacity.',
        '对于一个人的成功与失败，态度所扮演的角色比能力更重要许多。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (70, 'Happiness belongs to  those who dream dreams and are ready to pay the price to make them come true.',
        '快乐属于那些敢于梦想，并且时刻准备去实现梦想的人。（Leon Suenens）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (71,
        'In a world that\'s changing really quickly, the only strategy that is guaranteed to fail is not taking risks.',
        '在一个瞬息万变的世界里，不冒任何险是唯一保证会失败的策略。（Mark Zuckerberg）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (72, 'A friend is someone who knows all about you and still loves you.', '朋友是了解你的全部且仍然爱你的人。（Elbert Hubbard）',
        NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (73, 'If I have seen further than others, it is by standing upon the shoulders of giants.',
        '如果我看得比其他人远，那是因为我站在巨人的肩膀上。（Isaac Newton）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (74, 'Readers are plentiful. Thinkers are rare.', '阅读的人很多，但思考的人很少。（Harriet Martineau）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (75, 'Our greatest enemies, the ones we must fight most often, are within.',
        '我们最大的敌人和最常遇到的敌人，就在我们的心里。（Thomas Paine）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (76, 'Live out of your imagination, not your history.', '以你的想像，不是你的过去来生活。（Stephen Covey）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (77, 'Don\'t let yesterday take up too much of today.', '别让过去之事占据今天太多时间。（Will Rogers）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (78, 'Find something you\'re passionate about and keep tremendously interested in it.',
        '善于发现自己的喜好，并对它保持极大兴趣。（Julia Child）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (79, 'The very best thing you can do for the whole world is to make the most of yourself.',
        '你能为世界做最好的事，就是完全发挥自己的才能。（ Wallace Wattles）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (80, 'The important thing is not to stop questioning. Curiosity has its own reason for existing.',
        '重要的是不要停止疑问，好奇心有其存在的理由。（Albert Einstein）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (81, 'You\'re not obligated to win. You\'re obligated to keep trying. To the best you can do everyday.',
        '你不一定要获胜，但你必须不断尝试，每天表现你最好的一面。（Jason Mraz）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (82,
        'The best and most beautiful things in the world cannot be seen or even touched. They must be felt with the heart.',
        '世上最美好的事物看不着也摸不到，它们只能用心感觉。（Helen Keller）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (83, 'Truly, would you not for less than that make the tour around the world? ',
        '讲真，难道人们真的不可能用更短的时间来环游地球一周吗？--《环游地球80天》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (84,
        'It is better to live your own destiny imperfectly than to live an imitation of somebody else’s life with perfection.',
        '不完美地走自己的命运比完美地模仿别人的生活还要好。（The Bhagavad Gita）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (85, 'In your pursuit of your passions, always be young. In your relationship with others, always be grown-up.',
        '追求兴趣时保持年轻，与人相处时保持成熟。(Tom Brokaw)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (86, 'The art of being happy lies in the power of extracting happiness from common things.',
        '快乐的艺术是由平凡提取快乐的能力。（Henry Ward Beecher）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (87, 'The great thing in the world is not so much where we stand as in what direction we are moving.',
        '这世界的美好之处不在于我们所处的位置，而在于我们所要到达的远方。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (88, 'Protect your innocence. Our innocence allows us to do what the practical mind prohibits.',
        '保护你的纯真，它让我们有勇气去做实际心态所不允许的事。（Vin Diesel）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (89, 'Action is the real measure of intelligence.', '行动是智慧的真正衡量标准。（Napolean Hill）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (90, 'Making someone happy is perhaps the humblest way of approaching happiness.', '获取快乐的最简单方法或许就是让他人感受到你的快乐。',
        NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (91, 'There are many more people trying to meet the right person than to become the right person.',
        '很多人想遇到对的人，而不是让自己成为对的人。（Gloria Steinem）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (92, ' Rejoice in hope, be patient in tribulation.', '从希望中得到欢乐，在苦难中保持坚韧。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (93, 'Do not quench your inspiration and your imagination. Do not become the slave of your model.',
        '不要熄灭你的灵感及想像，不要成为你楷模的奴隶。--梵高', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (94,
        'Sentimental children forever whining about how bitterly unfair your lives have been. Well,it may have escaped you notice, but life isn\'t fair.',
        '你这个令人失望多愁善感的小子，只会苦涩地抱怨生活多不公平。你可能没有注意到，生活本来就是不公平的。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (95, 'Life is made up of small pleasures. Happiness is made up of those tiny successes. ',
        '生活中有很多小确幸，那些看似微不足道的收获，却正是快乐之所在。（Norman Lear）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (96,
        'I’ve probably earned the right to screw up a few times. I don’t want the fear of failure to stop me from doing what I really care about.',
        '或许偶尔弄糟一切，但我不希望对失败的恐惧阻碍我做真正想做的事。（Emma Watson）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (97, 'Satisfaction lies in the effort, not in the attainment. Full effort is full victory.',
        '满足感在于不断的努力，而不是现有成就。全心努力定会胜利满满。（Mahatma Gandhi）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (98, 'You can\'t be brave if you\'ve only had wonderful things happen to you.',
        '如果你只经历过美好，何来勇敢？（Mary Tyler Moore）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (99, 'You never saw a very busy person who was unhappy.', '你从没见过一个忙碌的人不快乐。（ Dorothy Dix）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (100, 'Do not let what you cannot do interfere with what you can do.', '别让你不能做的事妨碍到你能做的事。（John Wooden）', NULL,
        NULL, NULL);
INSERT INTO `daily_word`
VALUES (101,
        'Visualize this thing that you want, see it, feel it, believe in it. Make your mental blue print, and begin to build.',
        '想你所想，去看、去感受、去相信。心里画一个蓝图，并努力让它绚烂多彩。（Robert Collier）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (102, 'Home. I\'ll go home. And I\'ll think of someway to get him back. After all, tomorrow is another day.',
        '家，我会回家，想办法让他回来。不管怎样，明天又是全新的一天。--《飘》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (103, 'For life goes not backward nor tarries with yesterday.	', '生命不会后退，也不在过去停留。（纪伯伦）	', NULL, NULL,
        NULL);
INSERT INTO `daily_word`
VALUES (104, 'Love is that condition in which the happiness of another person is essential to your own.',
        '爱是当另一个人的快乐影响你自己的快乐。（Robert Heinlein）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (105,
        'Make each day count by setting specific goals to succeed, then putting forth every effort to exceed your own expectations.',
        '每天设定一个目标，然后努力做到完美，甚至超乎你的想象。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (106, 'There are no limits. There are only plateaus, and you must not stay there –you must go beyond them.',
        '没有所谓的极限，看似险难的高原，你不是待在那里，而是超越它。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (107,
        'If all else remained, and he were annihilated, the universe would turn to a mighty stranger. I should not seem a part of it.',
        '如果你不在了，无论这个世界有多么好，它在我眼里也只是一片荒漠，而我就像是一个孤魂野鬼。--《呼啸山庄》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (108,
        'Once there were no two hearts so open, no feelings so in harmony. Now we are strangers, worse than strangers, for we may never become acquainted. ',
        '曾经的相濡以沫，到如今的形同陌路，甚至还不如路人，一切都回不去了。--简·奥斯汀《劝导》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (109, 'It matters not what you are thought to be, but what you are.', '重要的不是别人怎么看你，而是你是怎样的人。(Publilius Syrus)',
        NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (110,
        'Making millions of  friends is not a miracle. The miracle is to make a friend who will stand by you when millions are against you.',
        '朋友无数也敌不过当所有人都弃你而去时，有一个能坚定地站在你身旁。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (111, 'The loving person makes other people feel good, and he is usually a happy person himself.',
        '有爱心的人让人感到舒服，而他自己通常也是个快乐的人。（Benjamin Spock）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (112, 'Instead of complaining about it, I\'m just gonna go in every day and give it my all. ',
        '与其抱怨不停，不如卯足劲儿全力去做好它。--《生活大爆炸》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (113, 'You\'re not obligated to win. You\'re obligated to keep trying. To the best you can do everyday.',
        '你不一定要获胜，但你必须不断尝试，每天表现你最好的一面。（Jason Mraz）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (114,
        'I love three things in the world, the sun, the moon and you. The sun for the day. The moon for the night and you forever.	',
        '世之万物，吾爱有三：一曰为日，一曰为月，一曰为汝。日出昼也，月升夜也，爱汝恒也！', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (115, 'It is a characteristic of wisdom not to do desperate things.	',
        '不做孤注一掷的事是智慧的表现。（Henry David Thoreau）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (116, 'Done is better than perfect.	', '期望完美往往抵不过完成一件事。（Sheryl Sandberg）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (117, 'Govern your thoughts when alone, and your tongue when in company.	', '闲谈勿论人非，静坐常思己过。	', NULL, NULL,
        NULL);
INSERT INTO `daily_word`
VALUES (118, 'The man who attracts luck carries with him the magnet of preparation.',
        '吸引好运的人，总是在时刻准备着。（Clifton Fadiman）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (119, 'Don\'t tell me how hard you work. Tell me how much you get done.	',
        '你有多努力无需多谈，重要的是你完成了多少计划之中的事。（James Ling）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (120,
        'Oliver went up to the master, with his bowl in his hand. He felt very frightened, but also desperate with hunger.	',
        '奥利弗站了起来，手里捧着碗，胆战心惊地朝管事的走了过去。他极度饥饿又心存恐惧。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (121, 'The secret of success in life is for a man to be ready for his opportunity when it comes.	',
        '人生成功的秘密在于，当机会来临时，他已准备好。（Benjamin Disraeli）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (122,
        'Every new day begins with possibilities. It\'s up to us to fill it with the things that move us toward progress and peace. ',
        '以无限的可能性开始每一天。我们本身决定着这一天是否进步或安心。（Ronald Wilson Reagan）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (123, 'It is impossible to win the great prizes in life without running risks.',
        '不冒险，怎么能赢得人生中的种种精彩？（Theodore Roosevelt）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (124, 'The most wonderful thing about love is that it turns you and me to us.  ', '爱情中最美好的事，莫过于你和我，最终成为我们。	',
        NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (125, 'The harder you fall, the higher you bounce.	', '跌得越重，反弹得就越高。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (126,
        'Time erodes all such beauty, but what it cannot diminish is the wonderful workings of your mind, your humor, your kindness and your moral courage.',
        '时间可以吞噬一切，但它丝毫不能减少的是你伟大的思想、幽默、善良和勇气。--《小妇人》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (127, 'The greatest gift that you can give to others is the gift of unconditional love and acceptance.	',
        '你能给别人的最好礼物，是无条件的爱与包容。（Brian Tracy）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (128, 'Much effort, much prosperity.	', '越努力，越幸运。（Euripides）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (129, 'Acknowledging the good that you already have in your life is the foundation for all abundance.',
        '感恩生活中所有，是所有富足的基础。（ Eckhart Tolle）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (130,
        'And if God had gifted me with some beauty and much wealth, I should have made it as hard for you to leave me, as it is now for me to leave you. 	',
        '如果上帝赋予我美貌和财富，我一定要使你难以离开我，就像现在我难以离开你。--《简爱》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (131, 'When a man has put a limit on what he will do, he has put a limit on what he can do.	',
        '当一个人为自己将做的事设限，他也为自己能做的事设限。（Charles M. Schwab）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (132, 'In every triumph, there\'s a lot of try.	', '每个胜利背后都有许多尝试。（Frank Tyger）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (133, 'That there was no room for unhappiness as long as I was free. 	', '只要我是自由的，便没有不快乐的时刻。--《消失的女孩》', NULL,
        NULL, NULL);
INSERT INTO `daily_word`
VALUES (134, 'It is precisely the possibility of realizing a dream that makes life interesting.	',
        '有机会实现梦想让人生变得有趣。（Paulo Coelho）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (135, 'Keep your face to the sunshine and you cannot see the shadow.	', '保持面朝阳光，你将不会见到阴暗。（Helen Keller）',
        NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (136,
        'Adventure is a state of mind and spirit. It comes with faith, for with complete faith, there is no fear of what faces you in life or death.',
        '冒险是一种心态和精神。与之而来的是绝对的信念，这样无论生死，都无所畏惧。（Jacqueline Cochran）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (137, 'Beauty is how you feel inside, and it reflects in your eyes. It is not something physical.	',
        '美，可以从一个人的眼神中看到，但更重要的，它来自于内心。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (138, 'Any activity becomes creative when the doer cares about doing it right, or better.',
        '当你专注把事情做好，或做得比原来更好，任何事情都可以创新。（John Updike）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (139, 'You aspire to do great things? Begin with little ones.	', '想成就大事，就要从小事开始。（Augustine of Hippo）', NULL,
        NULL, NULL);
INSERT INTO `daily_word`
VALUES (140, 'It is a rough road that leads to the heights of greatness.	',
        '崎岖不平的道路将通往伟大崇高之地。（Lucius Annaeus Seneca）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (141, 'Either write something worth reading or do something worth writing.	',
        '要么书写值得阅读的事情，要么做值得被书写的事情。（Benjamin Franklin）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (142, 'Never reject an idea, dream or goal because it will be hard work. Success rarely comes without it.',
        '绝不要因为怕辛苦而拒绝一个想法、梦想或是目标，成功的路上难免伴随辛苦。（Bob Proctor）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (143, 'You don\'t overcome challenges by making them smaller but by making yourself bigger.	',
        '想要克服困难，不是把困难最小化，而是自己努力变得更强大。（John C. Maxwell）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (144,
        'Life is too short to waste. Dreams are fulfilled only through action, not through endless planning to take action.',
        '人生短暂，怎能虚度！梦想要靠行动来实现，绝非无终止的计划。（David J. Schwartz）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (145, 'Courage is what it takes to stand up and speak. Courage is also what it takes to sit down and listen.',
        '人不仅需要勇气站起来说话，也需要勇气坐下来倾听。（Winston Churchill）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (146, 'True liberty is to have power over oneself in all things.	', '真正的自由是在所有时候都能控制自己。--蒙田', NULL, NULL,
        NULL);
INSERT INTO `daily_word`
VALUES (147, 'If one cannot enjoy reading a book over and over again, there is no use in reading it at all.	',
        '如果一个人并不能享受阅读的乐趣，那阅读这件事就毫无意义可言。（Oscar Wilde）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (148, 'You manifest what you believe, not what you want.	', '在你身上所体现的，是你所相信的东西，而非你所想要的东西。（Sonia Ricotti）',
        NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (149, 'Confidence, never deny yourself of it, for it costs you nothing and leads to great things.	',
        '一定要时刻保持自信，它不花你一毛钱但可以带你至很棒的境界。（Vin Diesel）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (150, 'Most people spend more time and energy going around problems than in trying to solve them.',
        '大多数人都把时间和精力浪费在了问题本身，而不是试着解决问题。（Henry Ford）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (151, 'If you want something you\'ve never had, you must be willing to do something you\'ve never done.',
        '若想得到你从未拥有的东西，你得愿意做你从未做过的事。（Thomas Jefferson）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (152, 'You can fail at what you don\'t want, so you might as well take a chance on doing what you love.',
        '做不想做的事可能会失败，倒不如给自己一个机会做你爱做的事。（Jim Carrey）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (153,
        'As we express our gratitude, we must never forget that the highest appreciation is not to utter words, but to live by them.	',
        '当我们表达感谢时，我们绝不能忘记感恩的最高形式不是说出的话，而是实际的作为。（John Kennedy）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (154, 'One always has time enough, if one will apply it well.	', '人总是有足够的时间，如果他好好利用。--歌德', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (155, 'The most difficult thing is the decision to act. The rest is merely tenacity.',
        '最困难的是下决定，剩下的就要坚持了。（Amelia Earhart）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (156, 'Sometimes, the perfect person for you is the one you least expect. 	',
        '有时候，最适合你的人，恰恰是你最没有想到的那个人。--《傲慢与偏见》	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (157,
        'A man, as a general rule, owes very little to what he is born with - a man is what he makes of himself.	',
        '一般而言，天生的能力对人的一生影响不大，真正影响他一生的是如何造就自己。（Alexander Graham Bell）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (158, 'Intelligence without ambition is a bird without wings.	', '聪明但没有抱负，有如没有翅膀的鸟。（Salvador Dali）	', NULL,
        NULL, NULL);
INSERT INTO `daily_word`
VALUES (159, 'Happiness hides in life\'s small details. If you\'re not looking, it becomes invisible.	',
        '快乐隐藏在生活的细节里，不留意就难以察觉。（Joyce Brothers）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (160, 'Go placidly, amid the noise and the haste, and remember what peace there may be in silence.	',
        '怡然行走于喧嚣繁忙中，莫忘寂静中或有之平和。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (161, 'The only way to survive was to enjoy the good moments and not dwell too much on the bad. ',
        '生活，就应该享受美好的时刻，切莫执着于不好的瞬间。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (162, 'Great works are performed not by strength, but by perseverance. ', '成大事者，凭毅力而非气力也。（Samuel Johnson）', NULL,
        NULL, NULL);
INSERT INTO `daily_word`
VALUES (163,
        'We all have our own life to pursue, our own kind of dream to be weaving, and we all have the power to make wishes come true, as long as we keep believing.',
        '我们都有自己要追求的人生，自己要编织的梦想，我们都有能力实现愿望，只要我们持续相信。（Louisa May Alcott）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (164, 'You\'re never a loser until you quit trying.', '你从来都不会成为输家，除非你停止尝试。（Mike Ditka）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (165,
        'Life is a series of collisions with the future. It is not the sum of what we have been, but what we yearn to be. ',
        '生活就是一连串与未来的碰撞，它不是我们过去的总和，而是我们对于未来的渴望。（Jose Ortega y Gasset）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (166,
        'The reasonable man adapts himself to the world. The unreasonable one persists in trying to adapt the world to himself.',
        '理性的人让自己适应世界；非理性的人坚持让世界适应自己。(George Bernard Shaw)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (167, 'In order to do something you\'ve never done, you have to become someone you\'ve never been.	',
        '为了做你从没做过的事，你必须成为另外一个人。（Les Brown）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (168, 'The course of true love never did run smooth. 	', '真爱无坦途。--莎士比亚《仲夏夜之梦》	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (169,
        'The conditions of conquest are always easy. We have but to toil awhile, endure awhile, believe always, and never turn back.	',
        '克服困难的条件一直都很简单。我们只需要努力一会儿、坚持一会儿、永远保持信念，且绝不折返。（ Lucius Annaeus Seneca）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (170, 'The only way to achieve the impossible is to believe that it is possible.	',
        '实现“不可能”的唯一办法就是相信它是可能的。--《爱丽丝漫游奇境记》	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (171, 'There are three constants in life…change, choice and principles.', '生活中有三样是不变的：改变，选择和原则。', NULL, NULL,
        NULL);
INSERT INTO `daily_word`
VALUES (172, 'If you\'re quiet, you\'re not living. You\'ve got to be noisy and colorful and lively.',
        '生活，没必要太过安静。只有喧闹、多姿多彩且活泼生动才能算是真正的生活。（Mel Brooks）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (173, 'Being defeated is often a temporary condition. Giving up is what makes it permanent.	',
        '被击垮通常只是暂时的，但如果你放弃的话，就会使它成为永恒。（Marilyn vos Savant）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (174,
        'Losing is a learning experience. It teaches you humility. It teaches you to work harder. It\'s also a powerful motivator.	',
        '失败是一个学习经验，它教你谦逊，让你更努力，它也是一个强大的激励来源。（Yogi Berra）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (175, 'The reading of all good books is like a conversation with the finest men of past centuries.	',
        '读好书，如同与先哲们交谈.（Rene Descartes）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (176, 'People who live the most fulfilling lives are the ones who are always rejoicing at what they have.',
        '永远对所拥有感到欣喜的人，是生活最富足的人。（Richard Carlson）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (177,
        'Your living is determined not so much by what life brings to you as by the attitude you bring to life.	',
        '你的人生主要不是取决于生活带给你什么，而是你带何种态度至生活里。（Khalil Gibran）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (178, 'They always say time changes things, but you actually have to change them yourself.	',
        '人们常说某些事会随时间改变，但事实上你必须自己改变它们。（Andy Warhol）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (179, 'The only limits in our life are those we impose on ourselves.	',
        '生活中的唯一限制是我们加诸在自己身上的。（Bob Proctor）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (180, 'Many people lose the small joys in the hope for the big happiness.',
        '很多人希望获得大的快乐，因而错失小的乐趣。（Pearl Buck）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (181, 'It is only with the heart that one can see rightly. What\'s essential is invisible to the eyes.	',
        '一个人只有用心去看，才能看到真实。事情的真相只用眼睛是看不见的。--《小王子》	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (182, 'I have nothing to offer but blood, toil, tears and sweat. ',
        '我能奉献的没有其它，只有热血、辛劳、眼泪与汗水。（ Winston Churchill）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (183, 'All I am, or can be, I owe to my angel mother. 	', '我之所有，我之所能，都归功于我天使般的母亲。(Abraham Lincoln)	', NULL,
        NULL, NULL);
INSERT INTO `daily_word`
VALUES (184,
        'The size of your success is measured by the strength of your desire, the size of your dream, and how you handle disappointment along the way.	',
        '成功是由你欲望的力量，梦想的大小，以及你如何处理一路上的失望来衡量的。（Robert T.Kiyosaki）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (185, 'To do nothing is the way to be nothing.	', '没有任何行动是通往一事无成的途径。（Nathaniel Hawthorne）	', NULL, NULL,
        NULL);
INSERT INTO `daily_word`
VALUES (186,
        'Life gives us choices…you either grab on with both hands and just go for it, or you sit on the sidelines.	',
        '人生有很多选择… 你不是双手握住索性跟着走，就是坐在一旁观看。（Christine Feehan）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (187,
        'You make me happier than I ever thought I could be. And if you\'ll let me, I will spend the rest of my life trying to make you feel the same way. 	',
        '你给了我难以想像的幸福。如果你愿意，我愿意用我的一生让你感受到同样的幸福。--《老友记》	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (188, 'The two most powerful warriors are patience and time.	', '时间与耐心是最强大的两个战士。(Leo Tolstoy)	', NULL,
        NULL, NULL);
INSERT INTO `daily_word`
VALUES (189, 'The difference in winning and losing is most often…not quitting.',
        '赢和输的区别在于，大部分的时候….不要放弃。（Walt Disney）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (190, 'Mind and Hand.', '理论与实践并重。--麻省理工学院校训', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (191, 'I\'d rather be a failure at something I love than a success at something I hate.',
        '我宁可做我爱做的事而失败，也不要做我讨厌的事而成功。（George Burns）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (192,
        'Successful people don\'t have fewer problems. They have determined that nothing will stop them from going forward.	',
        '成功的人也会遇到很多问题，他们只是坚信没有东西可以阻止他们前进。（Ben Carson）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (193, 'When the going gets tough, the tough gets going．	', '艰难之路，唯勇者行。（Caroline Seebohm）	', NULL, NULL,
        NULL);
INSERT INTO `daily_word`
VALUES (194, 'Cease to struggle and you cease to live.	', '生命不止，奋斗不息。（Thomas Carlyle）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (195,
        'Happiness lies not in the mere possession of money. It lies in the joy of achievement, in the thrill of creative effort.	',
        '幸福不在于拥有金钱，而在于获得成就时的喜悦以及产生创造力的激情。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (196, 'Today, give a stranger one of your smiles. It might be the only sunshine he sees all day.	',
        '今天，给一个陌生人送上你的微笑吧。很可能，这是他一天中见到的唯一的阳光。（H.Jackson Brown Jr.）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (197, 'Reading makes a full man, conference a ready man, and writing an exact man.', '阅读使人充实，交谈使人机智，写作使人精确。（培根）',
        NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (198,
        'Happiness is within. It has nothing to do with how much applause you get or how many people praise you. Happiness comes when you believe that you have done something truly meaningful.',
        '快乐是发自内心的。它与你获得了多少掌声，赢得了多少赞扬无关。快乐的真正意义在于，你相信，自己所奋斗的一切都有意义。	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (199, 'A mother\'s voice is the most beautiful sound in the world.	', '世界上有一种最美丽的声音，那便是母亲的呼唤。（但丁）	', NULL,
        NULL, NULL);
INSERT INTO `daily_word`
VALUES (200, 'A beautiful form is better than a beautiful face; a beautiful behavior than a beautiful form. 	',
        '美丽的外形胜于美丽的脸蛋，美丽的举止胜于美丽的外形。（Ralph Waldo Emerson）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (201, 'Genius only means hard-working all one\'s life .', '天才只意味着终身不懈的努力。 (俄国化学家，门捷列耶夫) 	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (202, 'The principal thing in this world is to keep one\'s soul aloft.	',
        '人生在世，首要大事就是保持灵魂的高尚。（Gustave Flaubert）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (203, 'The only limit to our realization of tomorrow will be our doubts of today.	',
        '实现明天理想的唯一障碍是今天的疑虑。（Franklin D. Roosevelt）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (204,
        'A new day will come. And when the sun shines, it will shine out the clearer...Folk in these stories had lots of chances of turning back, they kept going because they trust that\'s some good in this world.	',
        '崭新的一天将会来临，太阳也会散发出更明亮的光芒。这些故事中的主角，有很多机会半途而废，但他们永往直前，因为他们相信这世上一定存在着善良。--电影《指环王·双塔奇兵》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (205, 'A man can succeed at almost anything for which he has unlimited enthusiasm.	',
        '无论何事，只要对它有无限的热情你就能取得成功。（C. M. Schwab）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (206, 'And those who were seen dancing were thought to be insane by those who could not hear the music. ',
        '那些听不见音乐的人认为那些跳舞的人疯了。（尼采）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (207, 'Home is the place where, when you have to go there,they have to take you in.	',
        ' 无论何时何地,家永远是向游子敞开大门的地方。(Frost Robert)	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (208, 'Made weak by time and fate, but strong in will. To strive, to seek, to find, and not to yield. 	',
        '尽管被时间消磨，被命运削弱，依旧去奋斗、探索、寻求，而不屈服。--丁尼生《尤利西斯》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (209,
        'There are two things to aim at in life: first, to get what you want; and ,after that, to enjoy it , only the wisest of mankind achieve the second.	',
        '人的一生有两大目标：第一，得到你想要的东西；第二，享有你得到的东西。只有最聪明的人才能实现第二目标。（P. Smith Logan）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (210, 'Pride relates more to our opinion of ourselves, vanity to what we would have others think of us. ',
        '骄傲多数情况下，无非是我们对自己的看法，但虚荣却指的是我们过于看重其他人对我们的评价。--电影《傲慢与偏见》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (211, 'Reality is merely an illusion, albeit a very persistent one.', '现实仅仅是一种假象，挥之不去得假象。（阿尔伯特·爱因斯坦）	', NULL,
        NULL, NULL);
INSERT INTO `daily_word`
VALUES (212, 'A great secret of success is to go through life as a man who never gets used up.',
        '成功的秘诀是：经历人生，就像一个永远不会疲惫的人。（史怀哲）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (213, 'Put your heart, mind, and soul into even your smallest acts. This is the secret of success. 	 ',
        '即便是再微小不过的事情，你也要用心去做，这就是成功的秘密。（斯瓦米·悉瓦南达）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (214,
        'The first and greatest victory is to conquer yourself; to be conquered by yourself is of all things most shameful and vile. ',
        '战胜自己是最首要、最伟大的胜利，无法克制自己是最令人羞愧、最可怜的失败。（柏拉图）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (215, 'There is no doubt that good things will come, and when it comes, it will be a surprise. 	',
        '毋庸置疑，好的事情总会到来。而当它来晚时，也不失为一种惊喜。--电影《托斯卡纳艳阳下》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (216, 'Goals are not only absolutely necessary to motivate us. They are essential to really keep us alive. ',
        '我们不仅仅需要目标来保持前进的动力。我们需要有目标才能真正活下去。（Robert H. Schuller）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (217, 'No spring nor summer beauty hath such grace, as I have seen in one autumnal face.',
        '无论是春天的俏还是夏天的美，都没有秋天的这份优雅。（John Donne）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (218,
        'A pessimist sees the difficulty in every opportunity; an optimist sees the opportunity in every difficulty.',
        '悲观主义者在每个机会里看到困难；乐观主义者在每个困难里看到机会。（温斯顿‧邱吉尔）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (219,
        'We all have our time machines. Some take us back, they\'re called memories. Some take us forward, they\'re called dreams. ',
        '我们都有自己的时光机。带我们回到过去的，叫回忆；带我们前往未来的，叫梦想。（杰瑞米·艾恩斯）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (220, 'No matter what label is thrown your way, only you can define yourself.',
        '不管你被贴上什么标签，只有你才能定义你自己。--电影《丑女也有春天》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (221,
        'Regret for the things we did can be tempered by time. It is regret for the things we did not do that is inconsolable.',
        '为做过的事而产生的悔恨会因时间而被慢慢平抚；为没做过的事而后悔却是无法被平抚的。（西德尼·哈里斯）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (222,
        'I have always been delighted at the prospect of a new day, a fresh try, one more start, with perhaps a bit of magic waiting somewhere behind the morning.',
        '我总是对新的一天充满喜悦，这是一次新的尝试、一个新的开始，翘首以待，黎明之后或是惊喜。（约翰·博因顿·普里斯特利）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (223,
        'Thankfulness is the beginning of gratitude. Gratitude is the completion of thankfulness. Thankfulness may consist merely of words. Gratitude is shown in acts.',
        '感恩始于感谢之心，谢意正好完成了感恩。感恩也许只是些许的文字，感谢却要以行动去表达。（亨利·弗雷德里克·埃米尔）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (224,
        'As I began to love myself, I found that anguish and emotional suffering are only warning signs that I was living against my own truth. Today, I know, this is \"AUTHENTICITY\".',
        '当我真正开始爱自己，我才认识到，所有的痛苦和情感的折磨，都只是提醒我：我的生活违背了自己的本心。今天我明白了，这叫做“真实”。--卓别林《当我开始爱自己》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (225,
        'There is nothing noble in being superior to some other man. The true nobility is in being superior to your previous self. ',
        '优于别人，并不高贵，真正的高贵应该是优于过去的自己。（海明威）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (226, 'Celebrate what you\'ve accomplished, but raise the bar a little higher each time you succeed.',
        '庆祝你所取得的成就，但在每次成功后，把标准再拉高一点。（米娅·哈姆）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (227,
        'Happiness always looks small while you hold it in your hands, but let it go, and you learn at once how big and precious it is. ',
        '你把幸福捧在手里，看似微不足道，一旦放手，你便立刻感觉到它的重要与珍贵。（玛克西姆·高尔基）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (228, 'As long as you don\'t care about gains and losses, life is not what can not be overcome.',
        '只要不计较得失，人生便没有什么不能克服。（海明威）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (229, 'Good friends, good books, and a sleepy conscience: this is the ideal life. ',
        '益友、良书和一颗宁静的心，这就是理想的生活。（马克·吐温）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (230, 'Those who are crazy enough to think they can change the world, then he can really change the world.',
        '那些疯狂到以为自己能改变世界的人，才能真正改变世界。（史蒂夫•乔布斯）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (231,
        'The three great essentials to achieve anything worthwhile are, first, hard work; second, stick-to-itiveness; third, common sense.',
        '为达成任何有意义的事，需要三个关键条件，第一是努力，第二是坚持，第三是常理判断。（托马斯‧爱迪生）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (232, 'Luck is not chance, it\'s toil. Fortune\'s expensive smile is earned.',
        '幸运不是偶然，而是靠努力。命运之神昂贵的微笑是靠努力赢得的。（埃米莉·狄更生）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (233,
        'It eluded us then, but that\'s no matter. Tomorrow, we will run faster, stretch out our arms farther, and one fine morning.',
        '梦想尽管躲避我们吧，无关紧要！明天，我们会跑得更快，伸展得更远，又将是一个晴朗的早晨。--电影《了不起的盖茨比》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (234, 'I was not looking for my dreams to interpret my life, but rather for my life to interpret my dreams. ',
        '我不想用梦想来注解人生，我要用人生来诠释我的梦想。（美国作家，苏珊·桑塔格）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (235,
        'Cleverness is a gift while kindness is a choice. Gifts are easy - they’ re given after all. Choices can be hard.	',
        '聪明是一种天赋，而善良是一种选择。天赋得来容易——毕竟，它们是与生俱来的，但选择往往很困难。（杰夫·贝佐斯）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (236,
        'I was struggling to keep up some of the time and the scenery was going past way too fast and close at times.	',
        '我努力跟上时间的脚步，因为眼前的风景有时逝去得太快。（约翰·邦德）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (237,
        'To acquire the habit of reading is to construct for yourself a refuge from almost all the miseries of life. ',
        '养成读书的习惯，就是为自己建造一处几乎可以逃避生活中苦难的庇护所。（英国剧作家，威廉·萨默塞特·毛姆）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (238,
        'Three grand essentials to happiness in this life are something to do, something to love, and something to hope for. 	',
        '幸福人生的三要素是：有所为、有所爱、有所希望。(英国散文家，约瑟夫·艾迪生)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (239,
        'Change will not come if we wait for some other person or some other time. We are the ones we\'ve been waiting for. We are the change that we seek. ',
        '如果我们总是等待，改变永远不会到来。自己才是我们在等的那个人，才是我们在等的那个改变。（奥巴马）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (240, 'But man is not made for defeat. A man can be destroyed but not defeated.',
        '但人不是为失败而生的。一个人可以被毁灭，但不能被打败。 --海明威《老人与海》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (241,
        'A man is not old as long as he is seeking something. A man is not old until regrets take the place of dreams. ',
        '只要一个人还有追求，他就没有老。直到后悔取代了梦想，一个人才算老。（美国演员，约翰·巴里莫尔）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (242, 'Do not, for one repulse, forgo the purpose that you resolved to effect.',
        '不要只因一次挫败，就放弃你原来决心想达到的梦想。（莎士比亚）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (243,
        'For what it\'s worth, it\'s never too late, or in my case, too early, to be whoever you want to be. There\'s no time limit, start whenever you want.',
        '一件事无论太晚或者太早，都不会阻拦你成为你想成为的那个人。这个过程没有时间的期限，只要你想，随时都可以开始。--电影《返老还童》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (244,
        'How many loved your moments of glad grace. And loved your beauty with love false or true. But one man loved the pilgrim soul in you. And loved the sorrows of your changing face.',
        '多少人爱过你昙花一现的身影，爱过你的美貌，以虚伪或真情，惟独一人曾爱你那朝圣者的心，爱你哀戚的脸上岁月的留痕。--叶芝《当你老了》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (245,
        'I will love the light for it shows me the way, yet I will endure the darkness because it shows me the stars.',
        '我爱光明，因它为我指引道路；我也愿忍受黑夜，因它让我看到星辰。（奥格·曼狄诺）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (246, 'You know some birds are not meant to be caged. Their feathers are just too bright.',
        '你知道，有些鸟儿是注定不会被关在牢笼里的，它们的每一片羽毛都闪耀着自由的光辉。--电影《肖申克的救赎》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (247, 'Ordinary people merely think how they shall spend their time. A man of talent tries to use it.',
        '普通人只想到如何度过时间，而有才能的人会设法利用时间。(德国哲学家，亚瑟·叔本华)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (248,
        'Man is always more than he can know of himself. Consequently, his accomplishments, time and again, will come as a surprise to him.',
        '人们总是比所了解的自己还强，因此，人们的成就，将一再超出自己的意料。（戈洛·曼）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (249, 'Happiness is not something you postpone for the future. It is something you design for the present.',
        '快乐不是你延后将来要做的事，而是你计划现在要做的事。（励志作家，吉姆．罗恩）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (250,
        'Youth is not a time of life. It is a state of mind. It is not a matter of rosy cheeks, red lips and supple knees. It is a matter of the will, a quality of the imagination, a vigor of the emotions.',
        '青春不是芳华年少，而是一种心境；青春不是桃花红颜、盈盈朱唇、轻柔体态，而是积极的心志，丰富的想象，炙热的感情。（塞缪尔）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (251, 'The greater the struggle, the more glorious the triumph.', '挑战愈艰巨，胜利愈辉煌。（励志演说家，尼克‧胡哲)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (252,
        'Do what makes you happy, be with who makes you smile, laugh as much as you breathe, and love as long as you live.',
        '做让你开心的事，和让你开心的人在一起，让笑如呼吸般频繁，让爱像生命一样长久。（雷切尔·安·努内斯）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (253,
        'If you have great talents, industry will improve them. If you have but moderate abilities, industry will supply their deficiency.',
        '如果你很有天赋，勤勉会使其更加完善；如果你能力一般，勤勉会补足其缺陷。（乔舒亚·雷诺兹）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (254, 'The key to a happy life is to accept you are never actually in control.',
        '幸福生活的关键是要接受你永远无法真正掌控一切的现实。--电影《侏罗纪公园》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (255,
        'Better not to ignore the past but learn from it instead. Otherwise, history has a way of repeating itself. 	',
        '最好不要忽略过去，而是从中得到些教训，否则 历史会不断重复上演。--《绯闻女孩》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (256, 'Tough times never last, but tough people do.', '艰难的时光不会太久，但坚强的人会走到最后。（罗伯特·舒勒）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (257, 'Nature gives you the face you have at 20, it is up to you to merit the face you have at 50. ',
        '二十岁的美丽是天生的，五十岁的魅力是修养而得的。（加布里埃·香奈儿）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (258, 'Friend is someone who can see the truth and pain in you even when you are fooling everyone else.',
        '真正的朋友就是，当你蒙蔽了所有人的眼睛，也能看穿你真实的样子和心底的痛楚。《绝望主妇》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (259, 'Setting goals is the first step in turning the invisible into the visible.',
        '设计目标是将愿景清晰化的第一步。（美国励志演说家，安东尼·罗宾）	', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (260,
        'Home is the only hiding place of human shortcomings and failures in the world, it also hides the sweet love. --Bernard Shaw',
        '家是这个世界上唯一能隐藏人类缺点与失败的地方，它也隐藏着甜蜜的爱。(萧伯纳)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (261, 'Life is about choices. Some we regret. Some we\'re proud of. We are what we choose to be. -Graham Brown',
        '生活就是不断地选择。有些选择会让我们后悔，有些选择却又让我们感到自豪。但不管怎样，我们应该选择自己的人生。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (262, 'The only thing we have to fear is fear itself. --Franklin D. Roosevelt', '唯一能够让我们恐惧的，就是恐惧本身。', NULL, NULL,
        NULL);
INSERT INTO `daily_word`
VALUES (263, 'Action may not always bring happiness, but there is no happiness without action.--Benjamin Disraeli',
        '行动不一定总能带来快乐，但什么都不做就绝无快乐可言。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (264, 'When you want to succeed, you should use persistence as your good friend. ', '当你希望成功，当以恒心为良友。（爱迪生）', NULL,
        NULL, NULL);
INSERT INTO `daily_word`
VALUES (265,
        'Life takes on meaning when you become motivated, set goals and charge after them in an unstoppable manner.',
        '当你有了动力，设定了目标并势如破竹般地为此而奋斗，人生便有了意义。（莱斯‧布朗）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (266, 'You may not be the most luminous of people, but as a conductor of light, you are unbeatable.',
        '或许你不是最闪闪发光的那个，但是作为光的传递者，你也一样会举世无双。《神探夏洛克》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (267,
        'We do not need magic to transform our world. We carry all the power we need inside ourselves already.--J.K.Rowling',
        '我们不需要魔法来改变世界，因为我们的内心就已经拥有了所有力量。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (268, 'Our destiny offers not the cup of despair, but the chalice of opportunity. ',
        '命运给予我们的不是失望之酒，而是机会之杯。（尼克松）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (269,
        'Few things are impossible in themselves, and it is often for want of will, rather than of means, that man fails to succeed. ',
        '事情很少有根本做不成的，之所以做不成，与其说是条件不够，不如说是由于决心不够。（法国作家，罗切福考尔德）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (270, 'We cannot do great things on this earth, only small things with great love.--Mother Teresa',
        '我们无法在这个世界上做什么伟大的事情，但我们可以带着伟大的爱去做一些小事。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (271, 'Accept the things you cannot change. Have the courage to change the things you can.',
        '不能改变的事，要学会接受，而能够改变的事就要勇于行动。电影《正义联盟》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (272, 'Just living is not enough... one must have sunshine, freedom, and a little flower.',
        '仅仅活着是不够的，我们还需要有阳光、自由，和一点花的芬芳。（安徒生）', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (273, 'Pride relates more to our opinion of ourselves, vanity to what we would have others think of us. ',
        '骄傲多半是我们对自己的估价，但虚荣却牵涉到我们希望别人对自己的看法。《傲慢与偏见》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (274, 'May there be enough clouds in your life to make a beautiful sunset.', '愿你生命中有够多的云翳，来造成一个美丽的黄昏。—冰心《谈生命》',
        NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (275,
        'When things go wrong, running away is not the answer. You have to find within yourself a  way to handle it.',
        '当遇到困难的时候，逃避并不是答案。你必须自己想办法去解决。《快乐的大脚2》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (276, 'Success is the ability to go from one failure to another with no loss of enthusiasm.',
        '成功是，你一次又一次失败，却仍没有失去热情。(Winston Churchill)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (277, 'Everybody does stupid things, it shouldn\'t cost them everything they want in life.',
        '人人都会犯傻，不该因此剥夺他们全部的生活。《豪斯医生》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (278, 'If you do not learn to think when you are young, you may never learn.', '如果你年轻时不学会思考，那就永远不会。(爱迪生)', NULL,
        NULL, NULL);
INSERT INTO `daily_word`
VALUES (279,
        'Worry is a total waste of time. It doesn\'t change anything. All it does is steal your joy and keep you very busy doing nothing.',
        '担心纯属浪费时间，又改变不了什么。它只会偷走你的快乐让你很忙却什么也解决不了。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (280, 'The only person you should compare yourself to, is the person you were yesterday.',
        '唯一能够和你相比较的，就是那个曾经的自己。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (281,
        'We\'re all traveling through time together, every day of our lives. All we can do is do our best to relish this remarkable ride.',
        '我们生活的每一天，都在穿越时空，我们所能做的，就是尽其所能，珍惜这趟不平凡的旅程。《时空恋旅人》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (282, 'We are what we repeatedly do. Excellence, then, is not an act, but a habit.',
        '我们重复的行为造就了我们。因此，优秀不是一个行为，而是一种习惯。(亚里士多德)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (283, 'The best thing about the future is that it comes one day at a time.', '关于未来最美好的事就是它总有一天会到来。(林肯)', NULL,
        NULL, NULL);
INSERT INTO `daily_word`
VALUES (284, 'God gave you them shoes to fit you, so put them on and wear them.',
        '上帝给了你属于你的鞋子，请系好鞋带，整装待发。(Eminem-Beautiful Lyrics)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (285, 'Not everything is meant to be. But everything is worth a try.', '不是所有事情都能如愿以偿，但是任何事情都值得尝试。', NULL, NULL,
        NULL);
INSERT INTO `daily_word`
VALUES (286,
        'Learn from every mistake, because every experience, particularly your mistakes, are there to teach you and force you into being more of who you are.',
        '要从错误中吸取教训，因为你的每一次经历、尤其是你犯下的错误，都将帮助你、推动你更好地做自己。(Oprah Winfrey)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (287, 'My heart was pounding the whole time. It was the most erotic moment of my life. Up until then, at least.',
        '当时我的心一直像小鹿乱撞，那应该是我这辈子最性感的一刻了，至少在此之前没有过。《泰坦尼克号》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (288, 'When you finally let go of the past, something better comes along.', '当你最终放开了过去，更好的事就会到来。', NULL, NULL,
        NULL);
INSERT INTO `daily_word`
VALUES (289, 'Our lives are brief, that\'s why it\'s important to search for meaning.',
        '人的一生如此短暂，因此，找到活着的意义至关重要。《绝望主妇》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (290, 'Learn from the mistakes of others. You can never live long enough to make them all yourself.',
        '从别人的错误中学习，因为你无法什么都亲身经历一次。(Groucho Marx)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (291, 'For without progress there will be stagnation and decay. ', '如果没有进步，就会停滞，就会衰败。《哈利波特与凤凰社》', NULL, NULL,
        NULL);
INSERT INTO `daily_word`
VALUES (292, 'The most important thing in life will always be the people in this room, right here, right now.',
        '生活中最重要的就是屋檐下的人，就在此时，就在此地。《速度与激情7》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (293, 'Don\'t judge people by their covers. Most of their books are still being written.',
        '不要根据封面评判一个人，他们的人生还在书写。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (294, 'If it is important to you, you will find a way. If not, you\'ll find an excuse.',
        '如果你认为重要，总会想到办法。如果你认为不重要，总会找理由。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (295, 'You must continue to dream the wild dream that you dreamed when you were young.',
        '你必须不停地追逐年轻时的疯狂梦想。(Mike Tomlin)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (296, 'It doesn\'t matter what they call you. It\'s the deeds that make the man.', '他们怎么叫你并不重要，重要的是你自己要怎么做。《兰戈》',
        NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (297, 'The important thing in life is to have a great aim, and the determination to attain it.',
        '生命中很重要的一件事就是有一个明确的目标，并且有努力实现它的决心。(歌德)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (298, 'My dad was so full of life; anything with him was an adventure.', '我父亲是如此的充满生命力，与他在一起做任何事都是一种探险。(兰迪·波许)',
        NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (299, 'We all make our choices in life. The hard thing to do is live with them.',
        '我们在人生中会作出许多选择，带着这些选择继续生活，才是人生中最难的一课。《妙笔生花》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (300, 'Love is the master key that opens the gates of happiness.', '爱是打开幸福之门的万能钥匙。(Oliver Wendel Holmes)', NULL,
        NULL, NULL);
INSERT INTO `daily_word`
VALUES (301, 'However mean your life is, meet it and live it; do not shun it and call it hard names.',
        '不论你的生活多么糟糕，你要面对，要好好生活，不要躲避它，更别用恶言咒骂它。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (302, 'True friends could get you through  the hard times, the sad times, and the confused times.',
        '真正的朋友会与你一同度过困难、伤心和烦恼的时刻。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (303, 'It is often in the darkest skies that we see the brightest stars.',
        '往往在最黑暗的夜空里，我们看到最闪亮的星星。(Richard Evans)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (304,
        'Weep not for roads untraveled. Weep not for paths left alone \'cause beyond every bend is a long blinding end.',
        '不要因未走过的旅途而哭泣，不要因错过的道路而伤心，因为每条岔路，都通向炫目的尽头。(Linkin Park)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (305, 'Never let success get to your head and never let failure get to your heart.',
        '不要让成功冲昏了头脑，也不要把失败记在心里。(Ziad K.Abdelnour)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (306, 'You can\'t just sit there and wait for life to come to you. You have to go get it.',
        '你不能无所事事的坐等着一切发生，你必须得自己努力争取。《吸血鬼日记》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (307, 'You never know how strong you are, until being strong is your only choice.',
        '不到没有退路之时，你永远不会知道自己有多强大.(Bob Marley)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (308,
        'Sometimes it feels like, no matter how much success I have, it\'s not gonna matter until I find the right guy.',
        '有时候我会觉得，如果没找到命中注定的那个人，不管有多成功都没意义。《在云端》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (309, 'Growing old is mandatory; growing up is optional.', '变老是注定的，而成长却是可以选择的。(Chili Davis)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (310,
        'In the moment when I truly understand my enemy, understand him well enough to defeat him, then in that very moment I also love him.',
        '当我对敌人了解到足以让我打败他，那个时候，我也爱他。《安德的游戏》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (311, 'It\'s not having what you want. It\'s wanting what you\'ve got.', '生活不是追求你想要的，而是想要你已拥有的。(Sheryl Crow)',
        NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (312, 'Sometimes, all it takes is a little key to open the door to the wild side.',
        '有时候，我们需要的只是一把小小的钥匙来打开通向户外的门。《绯闻女孩》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (313, 'Life is full of inevitable highs and lows. Don\'t complain, don\'t explain, just maintain.',
        '人生难免有起落，不抱怨，不解释，要做的就是坚持！', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (314, 'If your dream was big enough and you had the guts to follow it, there was truly a fortune to be made.',
        '如果你的梦想足够远大，你又有勇气去追随它，财富便唾手可得。《奔腾年代》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (315, 'Each soul is individual and has its own merits and faults.', '每一个灵魂都是独特的，都有各自的美德和过错。《摆渡人》', NULL, NULL,
        NULL);
INSERT INTO `daily_word`
VALUES (316, 'The past cannot be changed. The future is yet in your power.', '过去无法改变，未来仍可掌控。(Mary Pickford)', NULL,
        NULL, NULL);
INSERT INTO `daily_word`
VALUES (317, 'Love is the thing that we\'re capable of perceiving that transcends dimensions of time and space.',
        '爱，是一种力量，可以让我们能够超越时空的维度来感知它的存在。《星际穿越》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (318, 'Never lose the child-like wonder.', '永远不要失去孩童般的好奇心。(兰迪·波许)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (319, 'It is not the destination so much as the journey, they say.', '人家说目的地不重要，重要的是旅行的过程。《加勒比海盗》', NULL, NULL,
        NULL);
INSERT INTO `daily_word`
VALUES (320, 'Life never stops pushing forward. Stay focused and never slow down with regrets.',
        '生活从来都不会停止向前，保持专注，别因为后悔而放慢脚步。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (321,
        'Everyone has his inherent power, which is easily concealed by habits, blurred by time, and eroded by laziness.',
        '每个人都有潜在的能量，只是很容易被习惯所掩盖，被时间所模糊，被惰性所消磨。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (322, 'The greatest risk in life is to risk nothing.', '人生中最大的风险是过没有任何风险的生活。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (323, 'Good luck is another name for tenacity of purpose.', '好运不过是坚持不懈的结果。(Ralph Waldo Emerson)', NULL, NULL,
        NULL);
INSERT INTO `daily_word`
VALUES (324, 'You can have it all. You just can\'t have it all at once.', '你可以拥有一切，只是不能一次就全到手。(Oprah Winfrey)', NULL,
        NULL, NULL);
INSERT INTO `daily_word`
VALUES (325, 'Laughter is timeless, imagination has no age, and dreams are forever.',
        '笑容永不过时，想象力不分年龄，梦想永不终结。(Walt Disney)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (326, 'All the beautiful sentiments in the world weigh less than a single lovely action.',
        '世间所有美丽的情感抵不过一次实际行动。(James Russell Lowell)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (327, 'Humility is not thinking less of yourself but thinking of yourself less.',
        '谦卑不是看轻自己，而是不仅为自己着想。(C.S. Lewis)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (328, 'Nothing really ever goes as planned.That\'s what makes life exciting.', '没有什么事情可以预先安排，这就是生活令人兴奋的地方。',
        NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (329, 'Courage is grace under pressure.', '勇气就是压力下的优雅。(海明威)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (330, 'Destiny is not a matter of chance. It\'s a matter of choice.', '命运不在于机会，而在于选择。(William Jennings Bryan)',
        NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (331, 'Individually we are weak, like a single twig but as a bundle we form a mighty faggot.',
        '一人拾柴火不旺，众人拾柴火焰高。《辛普森一家》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (332,
        'Sometimes, the key to making progress is to recognize how to take that very first step. Then you start your journey.',
        '有时候，进步的关键是要知道如何走出第一步，然后才能开启征程。《实习医生格蕾》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (333,
        'Take a method and try it. If it fails, admit it frankly, and try another. But by all means, try something.',
        '找到方法去尝试。如果失败了，坦然接受，尝试另外一种方法。但是无论如何，要去尝试。(Franklin D. Roosevelt)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (334, 'Be true to yourself and everything will be fine.', '做真实的自己，一切都会好起来的。(Ellen DeGeneres)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (335, 'Constant dropping wears away a stone.', '滴水穿石。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (336, 'The brave may not live forever, but the cautious do not live at all.', '勇气也许不能所向披靡，但胆怯根本无济于事。《公主日记》',
        NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (337, 'As is a tale, so is life: not how long it is, but how good it is, is what matters.',
        '人生就像故事：不在于有多长，而在于有多精彩，这才是最重要的。(Seneca，古罗马政治家、哲学家)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (338, 'Life\'s a climb, but the view is great.', '生活就像一场攀登，但风景美不胜收。《汉娜·蒙塔娜》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (339, 'A mother is not a person to lean on, but a person to make leaning unnecessary.',
        '母亲不是赖以依靠的人，而是让你有能力不依靠的人。(Dorothy Canfield Fisher)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (340, 'Better to do something imperfectly than to do nothing flawlessly.',
        '宁愿做事不完美，总比完美地不做事要好。(Robert Schuller)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (341, 'A winner is a dreamer who never gives up.', '成功者是坚持梦想不放弃的人。(Nelson Mandela)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (342, 'A life with love will have some thorns, but a life without love will have no roses.',
        '有爱的人生会有荆棘，但没有爱的人生将没有玫瑰。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (343, 'We\'re here to put a dent in the universe.', '我们存在，只为改变世界。(Steve Jobs)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (344, 'Forward! That is the battle cry.', '勇往直前才是我的作战口号。《纸牌屋》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (345, 'Innovation distinguishes between a leader and a follower.', '领袖和跟风者的区别就在于创新。(Steve Jobs)', NULL, NULL,
        NULL);
INSERT INTO `daily_word`
VALUES (346, 'You can\'t reach for anything new, if your hands are still full of yesterdays junk.',
        '如果你的双手仍被昨天的垃圾占据，你就无法获得任何新鲜的东西。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (347, 'If you love life, don\'t waste time, for time is what life is made up of.',
        '如果你爱生活，那就不要浪费时间，因为生活就是由时间组成的。(李小龙)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (348, 'Whatever your mind can conceive and believe, it can achieve.', '只要你想得到，只要你相信，就一定能做到！(拿破仑)', NULL, NULL,
        NULL);
INSERT INTO `daily_word`
VALUES (349, 'It\'s not the hours you put in your work that counts, it\'s the work you put in the hours.',
        '工作效益不在于时间长短，而在于真正做了什么。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (350, 'Life is a journey, not a destination.', '人生就是一场旅行，而不是一个终点。(Ralph Waldo Emerson)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (351, 'We should consider every day lost on which we have not danced at least once.', '每一个不曾起舞的日子，都是对生命的辜负。(尼采)',
        NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (352, 'A bend in the road is not the end of the road. Unless you fail to make the turn.',
        '路上的转弯处不是路的终点，除非你没有转弯。(Helen Keller)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (353, 'Life is simple. You make choices and you don\'t look back.', '生活很简单，作出选择，不回头。《速度与激情3东京漂移》', NULL, NULL,
        NULL);
INSERT INTO `daily_word`
VALUES (354, 'We do what we have to do so that we can do what we want to do.', '我们做了我们必须做的，所以才能做我们想做的。《伟大辩手》', NULL,
        NULL, NULL);
INSERT INTO `daily_word`
VALUES (355, 'You will never have what you like until you learn to like what you have.', '要得到你喜欢的东西，应先学会喜欢你已有的东西。(歌德)',
        NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (356, 'The reading of all good books is like a  conversation with the finest men of past centuries.',
        '读好书，就如同和过去世界上最杰出的人谈话。(勒内·笛卡儿)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (357, 'Go as far as you can see; when you get there, you\'ll be able to see farther.',
        '走到你能看到的最远处，当走到那，将能看得更远。(J. P. Morgan)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (358, 'The size of a man may be measured by the size of the things that make him angry.',
        '一个人的肚量取决于让他生气的事情的大小。(Adlai E. Stevenson)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (359, 'Constantly talking isn\'t necessarily communicating.', '说个不停不一定是交流。《美丽心灵的永恒阳光》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (360, 'To improve is to change; to be perfect is to change often.', '想提高就要改变，想达到完美就要不断改变。(温斯顿·丘吉尔)', NULL, NULL,
        NULL);
INSERT INTO `daily_word`
VALUES (361, 'The past can hurt. But from the way I see it, you can either run from it, or learn from it.',
        '过去是痛楚的，但我认为你要么可以逃避，要么可以向它学习。《狮子王》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (362, 'The individual has always had to struggle to keep from being overwhelmed by the tribe.',
        '一个人必须在社会中不断挣扎求存，才能不被茫茫人海湮灭。(尼采)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (363, 'It matters not what someone is born, but what they grow to be.', '一个人的出身并不重要，重要的是他成长为什么样的人！《哈利波特与火焰杯》',
        NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (364, 'For me life has got to be an adventure. Enjoy every minute of it.', '在我看来，人生应该是一场冒险。享受每一分钟的生活！(保罗·沃克)',
        NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (365, 'To travel hopefully is a better thing than to arrive.', '带着希望旅行比到达目的地更美好。(Robert Louis Stevenson)', NULL,
        NULL, NULL);
INSERT INTO `daily_word`
VALUES (366, 'Never let the demands of tomorrow interfere with the pleasures and excitement of today.',
        '永远别让明天的期待，影响你享受今天的时光。(Meredith Willson)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (367, 'Success is simple. Do what\'s right, the right way, at the right time.',
        '成功很简单：在合适的时间用恰当的方式做正确的事。(Arnold H. Glasgow)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (368, 'Don\'t tell me I can\'t do it. Don\'t tell me it can\'t be done.', '别告诉我这我做不到，别告诉我这事做不成。《飞行者》', NULL,
        NULL, NULL);
INSERT INTO `daily_word`
VALUES (369, 'Don\'t worry if you have had a bad day. We each have a reset button. It\'s called tomorrow.',
        '如果你今天过得很糟糕，别担心，我们都有一个重置按钮叫做“明天”。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (370, 'We dream and we build. We never give up; we never quit.', '我们有梦想，努力去实现。绝不放弃，绝不退缩。《美国骗局》', NULL, NULL,
        NULL);
INSERT INTO `daily_word`
VALUES (371, 'Unless you try to do something beyond what you have already mastered, you will never grow.',
        '不去尝试你已经熟练以外的事，你不会得到成长。(Ralph Waldo Emerson)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (372,
        'We all have moments of desperation. But if we can face them head on, that\'s when we find out just how strong we really are.',
        '我们都有绝望的时候，只有在勇敢面对时，才知道自己有多坚强。《绝望主妇》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (373, 'The true nobility is in being superior to your previous self.', '真正的高贵在于超越过去的自己。(海明威《真正的高贵》)', NULL, NULL,
        NULL);
INSERT INTO `daily_word`
VALUES (374,
        'The will to survive is extraordinary and at times overwhelming, capable of fashioning some form of normality even out of the darkest hour.',
        '人的求生意志是无比强烈的，有时甚至压倒一切，这意志使他们即便身处最黑暗的时刻也能坦然面对。《老牌政敌2：国会危机》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (375, 'Insanity is doing the same thing over and over again and expecting different results.',
        '疯狂就是重复做一件事，并期待不同的结果。(爱因斯坦)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (376,
        'Some of us get dipped in flat, some in satin, some in gloss. But every once in a while, you find someone who\'s iridescent, and when you do, nothing will ever compare.',
        '有些人会渐露平庸，有些人会小有所成，还有人会出类拔萃，但偶尔才会遇上一个光彩夺目的人，当你真正遇到，就发现其他都是浮云。《怦然心动》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (377, 'The most important thing is to enjoy your life - to be happy - it\'s all that matters.',
        '享受生活，快快乐乐，这才是最重要的。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (378, 'Write it on your heart that every day is the best day in the year.',
        '请记住：现在的每一天都是一年中最美好的一天。(Ralph Waldo Emerson)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (379, 'We mustn\'t fear daylight just because it almost always illuminates a miserable world.',
        '我们绝对不能因为阳光几乎只会照耀出一个悲惨的世界而惧怕它。(比利时超现实主义画家勒内·马格里特)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (380, 'There is no absolute success in the world, only constant progress.',
        '世界上的事没有绝对成功，只有不断的进步。(Jonathan Swift)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (381, 'The best way to achieve a goal is to devote 100% of your time and energy to it.',
        '达到目标的最好办法就是投入自己百分百的时间和精力去完成它。《生活大爆炸》', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (382, 'By failing to prepare, you are preparing to fail.', '不做准备，那就准备失败吧。(Benjamin Franklin)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (383, 'Nothing is so common-place as to wish to be remarkable.', '没有什么比希望不平凡而更平凡的了。(莎士比亚)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (384, 'Don\'t complain about things you are not willing to work hard to change.', '如果你不愿意努力去改变，那就不要抱怨。', NULL,
        NULL, NULL);
INSERT INTO `daily_word`
VALUES (385, 'It\'s fine to celebrate success but it is more important to heed the lessons of failure.',
        '庆祝成功是好事，但汲取失败的教训更重要。(比尔·盖茨)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (386, 'It\'s always helpful to learn from your mistakes because then your mistakes seem worthwhile.',
        '从错误中学习总是有益的，你的错误将因此变得值得。(Garry Marshall)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (387, 'Don\'t waste life in doubts and fears.', '不要把生命浪费在疑惑和恐惧中。(爱默生)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (388,
        'Being happy doesn\'t mean that everything is perfect. It means that you\'ve decided to look beyond the imperfections.',
        '快乐不代表一切都很完美，它意味着你决定去忽略那些不完美。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (389, 'There is no substitute for hard work. First in, last out.', '努力没有替代品，最先开始，最后离开。(Mary Callahan Erdoes)',
        NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (390, 'Only by coming to grips with difficulty can you realize your full potential.',
        '唯有直面及处理困难，你才能够发挥自己最大的潜能。(戴高乐将军)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (391,
        'Everyone needs to have a good laugh now and then, to see the funny side of things, and to laugh at himself.',
        '人有时总需要开怀大笑，看看事情轻松的一面，并懂得自嘲。(李光耀)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (392, 'The beauty of a woman is not in a facial mode, but the true beauty in a woman is reflected in her soul.',
        '女人的美丽不是表面的，应该是她的精神层面。(奥黛丽·赫本)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (393, 'Every accomplishment starts with the decision to try.', '任何成就都是从决定尝试开始。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (394, 'Things do not happen. Things are made to happen.', '事情不会自己发生，事情都是人做出来的。(美国前总统约翰.肯尼迪John F. Kennedy)',
        NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (395, 'Those who bring sunshine into the lives of others, cannot keep it from themselves.',
        '给别人的生命带来阳光的人，自己也会享有阳光。(J.M. Barrie)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (396, 'Confidence comes not from always being right but not fearing to be wrong.',
        '信心不是来自永远正确，而是来自不怕犯错。(Peter T. Mcintyre)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (397,
        'What we do during our working hours determines what we have; what we do in our leisure hours determines what we are.',
        '工作时间做的事，决定我们拥有什么；闲暇时间做的事，决定我们成为哪种人。(George Eastman)', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (398, 'The only limit is your imagination.', '唯一的局限是你的想象力。', NULL, NULL, NULL);
INSERT INTO `daily_word`
VALUES (399, 'Life is 10% what happens to me and 90% of how I react to it.', '人生，你遇到的事情占一成，你怎样对待这些事情占九成。', NULL, NULL,
        NULL);
INSERT INTO `daily_word`
VALUES (400, 'Where there is kindness, there is goodness, and where there is goodness, there is magic.',
        '哪里有仁爱，哪里就有善行，哪里有善行，哪里就有奇迹。《灰姑娘》', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for judge_host
-- ----------------------------
DROP TABLE IF EXISTS `judge_host`;
CREATE TABLE `judge_host`
(
    `id`          int(0) UNSIGNED                                               NOT NULL AUTO_INCREMENT,
    `name`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `base_url`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `create_time` datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0),
    `update_time` datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
    `delete_time` datetime(0)                                                   NULL DEFAULT NULL,
    `is_active`   tinyint(0)                                                    NULL DEFAULT NULL,
    `port`        int(0)                                                        NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of judge_host
-- ----------------------------
INSERT INTO `judge_host`
VALUES (100, 'JudgeHost1', 'http://judge-host', '2020-08-16 21:04:13', '2020-08-31 16:06:51', NULL, 1, 8080);

-- ----------------------------
-- Table structure for judge_problem
-- ----------------------------
DROP TABLE IF EXISTS `judge_problem`;
CREATE TABLE `judge_problem`
(
    `id`                       int(0) UNSIGNED                                               NOT NULL AUTO_INCREMENT,
    `name`                     varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '问题名称',
    `time_limit`               int(0) UNSIGNED                                               NULL DEFAULT 5 COMMENT '程序运行的时间限制，单位为毫秒',
    `memory_limit`             int(0) UNSIGNED                                               NULL DEFAULT 32768 COMMENT '程序运行的内存限制，单位为kb',
    `cpu_time_limit`           int(0) UNSIGNED                                               NULL DEFAULT 5 COMMENT '程序运行的时间限制(cpu)，单位为毫秒',
    `create_time`              datetime(3)                                                   NULL DEFAULT CURRENT_TIMESTAMP(3),
    `delete_time`              datetime(3)                                                   NULL DEFAULT NULL,
    `update_time`              datetime(3)                                                   NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    `character_tags`           json                                                          NULL COMMENT '题目标签',
    `accept_amount`            int(0) UNSIGNED                                               NULL DEFAULT 0 COMMENT '通过数量',
    `total_submisstion_amount` int(0) UNSIGNED                                               NULL DEFAULT 0 COMMENT '总提交数目',
    `is_closed`                tinyint(0) UNSIGNED                                           NULL DEFAULT 0,
    `output_limit`             int(0)                                                        NULL DEFAULT 50000,
    `content`                  longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of judge_problem
-- ----------------------------
INSERT INTO `judge_problem`
VALUES (10000, 'A+B Problem', 4000, 32768, NULL, '2020-08-06 20:41:18.026', NULL, '2020-09-11 18:43:18.413', '[
  \"入门\",
  \"测试\"
]', 0, 0, 0, 50000,
        'C\n\n```c\n#include <stdio.h>\n\nint main() {\n    int a,b;\n    scanf(\"%d%d\",&a,&b);\n    printf(\"%d\", a+b);\n    return 0;\n}\n```\n\nC++\n\n```cpp\n#include <iostream>\n#include <cstdio>\n\nusing namespace std;\n\nint main() {\n    int a,b;\n    cin >> a >> b;\n    cout << a+b;\n    return 0;\n}\n```\n\nPython3\n\n```cpp\ns = input().split()\nprint(int(s[0]) + int(s[1]))\n```\n\n\nJava\n\n```java\nimport java.io.*;\nimport java.util.*;\npublic class Main {\n    public static void main(String args[]) throws Exception {\n        Scanner cin=new Scanner(System.in);\n        int a = cin.nextInt(), b = cin.nextInt();\n        System.out.println(a+b);\n    }\n}\n```\n### 测试一下公式: \n$$\\int_0^\\infty x^2 dx$$');
INSERT INTO `judge_problem`
VALUES (10001, 'fork炸弹', 5000, 32768, NULL, '2020-09-04 18:10:01.249', NULL, '2020-09-11 18:43:18.418', '[
  \"测试\"
]', 0, 0, 0, 100000,
        '## 一些危险代码：\n\n###  此类代码会卡死评测机，我们可以通过限制系统调用来处理它(基于linux的评测机可使用seccomp，windows没试过)，最终我们得到一个RUNTIME_ERROR\n\n```c\n#include <stdio.h>\n#include <unistd.h>\n\nint main()\n{\n    if (!fork())\n    {\n        while (1)\n        {\n            fork();\n        }\n    }\n    return 0;\n}\n```\n\n### 此类代码会在编译时，造成评测机卡住，原因是让编译器从标准输入中读取源代码，而不是从源文件中读取，可以通过用户降权（改变uid）的方式解决，最终由于我们无权访问，得到了一个COMPILE_ERROR\n\n```c\n#include</dev/console> // linux\n#include<con> // windows\n```\n\n### 此类代码会使编译器产生大量的错误输出(在我使用的gcc编译器下并不起作用，可能对老版本有效)，我们可以调整编译器参数限制报错量。\n```c\nstruct x struct z<x(x(x(x(x(x(x(x(x(x(x(x(x(x(x(x(x(y,x(y><y*,x(y*w>v<y*,w,x{}\n```\n\n### 类似的，下面的代码会使编译器产生体积较大的输出文件，大概几十G， 我们可以使用linux下的setrlimit限制输出量。\n```c\nmain[-1u]={1};\n\n```');
INSERT INTO `judge_problem`
VALUES (10002, '矩阵乘法', 2000, 32768, NULL, '2020-09-07 19:01:33.065', NULL, '2020-09-11 18:43:18.423', '[
  \"数学\"
]', 0, 0, 0, 10000000, '## 本题目来源于互联网，仅供项目进行提交测试使用，若对题面感兴趣，请自行前去查看。');
INSERT INTO `judge_problem`
VALUES (10003, '并查集', 2500, 32768, NULL, '2020-09-07 20:24:46.110', NULL, '2020-09-11 18:43:18.426', '[
  \"并查集\"
]', 0, 0, 0, 100000, '## 本题目来源于互联网 ，仅供项目进行提交测试使用，若对题面感兴趣，请自行前去查看。');
INSERT INTO `judge_problem`
VALUES (10004, '质数判定', 3000, 32768, NULL, '2020-09-07 22:02:03.806', NULL, '2020-09-11 18:43:18.431', '[
  \"质数\"
]', 0, 0, 0, 10000000, '## 本题目来源于互联网，仅供项目进行提交测试使用，若对题面感兴趣，请自行前去查看。\n');
INSERT INTO `judge_problem`
VALUES (10005, '子串查找', 500, 32768, NULL, '2020-09-07 22:33:16.475', NULL, '2020-09-11 18:43:18.435', '[
  \"字符串\"
]', 0, 0, 0, 100000, '## 本题目来源于互联网，仅供项目进行提交测试使用，若对题面感兴趣，请自行前去查看。');
INSERT INTO `judge_problem`
VALUES (10006, '最长公共子串', 1000, 32768, NULL, '2020-09-07 22:47:00.826', NULL, '2020-09-11 18:43:18.438', '[
  \"字符串\"
]', 0, 0, 0, 100000, '## 本题目来源于互联网 ，仅供项目进行提交测试使用，若对题面感兴趣，请自行前去查看。');
INSERT INTO `judge_problem`
VALUES (10007, '最小费用流', 4000, 32768, NULL, '2020-09-07 23:06:53.563', NULL, '2020-09-11 18:43:18.442', '[
  \"模板\"
]', 0, 0, 0, 100000, '## 本题目来源于互联网 ，仅供项目进行提交测试使用，若对题面感兴趣，请自行前去查看。');
INSERT INTO `judge_problem`
VALUES (10008, '高精度除法', 1000, 50000, NULL, '2020-09-07 23:15:06.419', NULL, '2020-09-11 18:43:18.445', '[
  \"高精度\"
]', 0, 0, 0, 100000, '## 本题目来源于互联网 ，仅供项目进行提交测试使用，若对题面感兴趣，请自行前去查看。');
INSERT INTO `judge_problem`
VALUES (10009, '快速幂', 1000, 32768, NULL, '2020-09-07 23:29:51.585', NULL, '2020-09-11 18:43:18.448', '[
  \"快速幂\"
]', 0, 0, 0, 100000, '<p>您可以修改题目内容</p>\n');
INSERT INTO `judge_problem`
VALUES (10010, 'Best Cow Fences', 1000, 32768, NULL, '2020-09-07 23:40:25.442', NULL, '2020-09-11 18:43:43.875', '[
  \"USACO 2003\"
]', 0, 0, 0, 100000, '## 本题目来源于互联网 ，仅供项目进行提交测试使用，若对题面感兴趣，请自行前去查看。\n');
INSERT INTO `judge_problem`
VALUES (10011, '最大连续和', 1000, 32768, NULL, '2020-09-08 12:24:14.591', NULL, '2020-09-11 18:43:18.458', '[
  \"DP\"
]', 0, 0, 0, 100000, '## 本题目来源于互联网，仅供项目进行提交测试使用，若对题面感兴趣，请自行前去查看。\n');
INSERT INTO `judge_problem`
VALUES (10012, '字符串匹配', 4000, 32768, NULL, '2020-07-30 18:13:22.325', NULL, '2020-09-11 18:43:18.462', '[
  \"测试\"
]', 0, 0, 0, 50000,
        '## 本题目来源于 PTA ，仅供项目进行提交测试使用。\n\n给出一个最大长度为10^6的母串t，请你在t里面找到长度为len的出现次数最多的子串，如果找到多个出现次数一样的子串，请你输出字典序最小的。\n\n\n\n### 输入格式:\n\n在第一行输入一个正整数Len（Len<=10^6），第二行输入一个母串t，t的长度小于等于10^6。\n\n### 输出格式:\n\n输出答案子串和它在t中的出现次数，用一个空格分隔，行末尾没有多余空格！\n\n### 输入样例:\n\n在这里给出一组输入。例如：\n\n```in\n3\naba ababababababaaababababa\n```\n\n### 输出样例:\n\n在这里给出相应的输出。例如：\n\n```out\naba 11\n```\n');

-- ----------------------------
-- Table structure for judge_solution
-- ----------------------------
DROP TABLE IF EXISTS `judge_solution`;
CREATE TABLE `judge_solution`
(
    `id`               int(0) UNSIGNED                                               NOT NULL AUTO_INCREMENT,
    `std_in`           varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `expected_std_out` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `create_time`      datetime(3)                                                   NULL DEFAULT CURRENT_TIMESTAMP(3),
    `delete_time`      datetime(3)                                                   NULL DEFAULT NULL,
    `update_time`      datetime(3)                                                   NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    `pk_problem`       int(0)                                                        NULL DEFAULT NULL,
    `description`      varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of judge_solution
-- ----------------------------
INSERT INTO `judge_solution`
VALUES (1, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599542825760.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599542825761.out',
        '2020-09-08 13:27:26.686', NULL, '2020-09-08 13:27:26.699', 10002, '0');
INSERT INTO `judge_solution`
VALUES (2, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599542847845.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599542847845.out',
        '2020-09-08 13:27:33.130', NULL, '2020-09-08 13:27:33.142', 10002, '1');
INSERT INTO `judge_solution`
VALUES (3, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599542853812.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599542853812.out',
        '2020-09-08 13:27:40.738', NULL, '2020-09-08 13:27:40.747', 10002, '2');
INSERT INTO `judge_solution`
VALUES (4, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599542861688.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599542861688.out',
        '2020-09-08 13:27:53.083', NULL, '2020-09-08 13:27:53.095', 10002, '3');
INSERT INTO `judge_solution`
VALUES (5, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599542875919.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599542875919.out',
        '2020-09-08 13:28:02.381', NULL, '2020-09-08 13:28:02.391', 10002, '4');
INSERT INTO `judge_solution`
VALUES (6, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599542883255.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599542883255.out',
        '2020-09-08 13:28:10.118', NULL, '2020-09-08 13:28:10.126', 10002, '5');
INSERT INTO `judge_solution`
VALUES (7, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599542890695.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599542890695.out',
        '2020-09-08 13:28:17.121', NULL, '2020-09-08 13:28:17.130', 10002, '6');
INSERT INTO `judge_solution`
VALUES (8, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599542897937.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599542897937.out',
        '2020-09-08 13:28:24.476', NULL, '2020-09-08 13:28:24.486', 10002, '7');
INSERT INTO `judge_solution`
VALUES (9, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599542905337.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599542905337.out',
        '2020-09-08 13:28:31.630', NULL, '2020-09-08 13:28:31.641', 10002, '8');
INSERT INTO `judge_solution`
VALUES (10, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599542912230.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599542912230.out',
        '2020-09-08 13:28:38.502', NULL, '2020-09-08 13:28:38.512', 10002, '9');
INSERT INTO `judge_solution`
VALUES (11, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599542941908.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599542941908.out',
        '2020-09-08 13:29:28.621', NULL, '2020-09-08 13:29:28.630', 10003, '0');
INSERT INTO `judge_solution`
VALUES (12, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599542969886.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599542969886.out',
        '2020-09-08 13:30:11.361', NULL, '2020-09-08 13:30:11.367', 10003, '1');
INSERT INTO `judge_solution`
VALUES (13, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543020939.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543020939.out',
        '2020-09-08 13:30:30.036', NULL, '2020-09-08 13:30:30.056', 10004, '0');
INSERT INTO `judge_solution`
VALUES (14, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543030748.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543030748.out',
        '2020-09-08 13:30:35.920', NULL, '2020-09-08 13:30:35.927', 10004, '0');
INSERT INTO `judge_solution`
VALUES (15, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543037138.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543037138.out',
        '2020-09-08 13:30:43.852', NULL, '2020-09-08 13:30:43.860', 10004, '1');
INSERT INTO `judge_solution`
VALUES (16, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543045213.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543045213.out',
        '2020-09-08 13:30:57.439', NULL, '2020-09-08 13:30:57.446', 10004, '2');
INSERT INTO `judge_solution`
VALUES (17, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543058363.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543058363.out',
        '2020-09-08 13:31:08.661', NULL, '2020-09-08 13:31:08.671', 10004, '3');
INSERT INTO `judge_solution`
VALUES (18, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543069340.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543069340.out',
        '2020-09-08 13:31:24.674', NULL, '2020-09-08 13:31:24.682', 10004, '3');
INSERT INTO `judge_solution`
VALUES (19, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543160993.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543160993.out',
        '2020-09-08 13:32:51.161', NULL, '2020-09-08 13:32:51.172', 10011, '0');
INSERT INTO `judge_solution`
VALUES (20, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543172001.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543172001.out',
        '2020-09-08 13:32:57.709', NULL, '2020-09-08 13:32:57.725', 10011, '1');
INSERT INTO `judge_solution`
VALUES (21, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543178498.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543178498.out',
        '2020-09-08 13:33:05.196', NULL, '2020-09-08 13:33:05.205', 10011, '2');
INSERT INTO `judge_solution`
VALUES (22, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543186055.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543186055.out',
        '2020-09-08 13:33:12.599', NULL, '2020-09-08 13:33:12.608', 10011, '3');
INSERT INTO `judge_solution`
VALUES (23, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543194612.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543194613.out',
        '2020-09-08 13:33:21.888', NULL, '2020-09-08 13:33:21.898', 10011, '4');
INSERT INTO `judge_solution`
VALUES (24, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543203148.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543203148.out',
        '2020-09-08 13:33:30.369', NULL, '2020-09-08 13:33:30.380', 10011, '5');
INSERT INTO `judge_solution`
VALUES (25, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543211931.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543211931.out',
        '2020-09-08 13:33:40.745', NULL, '2020-09-08 13:33:40.753', 10011, '6');
INSERT INTO `judge_solution`
VALUES (26, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543221615.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543221615.out',
        '2020-09-08 13:33:50.198', NULL, '2020-09-08 13:33:50.207', 10011, '7');
INSERT INTO `judge_solution`
VALUES (27, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543238524.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543238524.out',
        '2020-09-08 13:34:08.118', NULL, '2020-09-08 13:34:08.125', 10010, '1');
INSERT INTO `judge_solution`
VALUES (28, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543249016.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543249016.out',
        '2020-09-08 13:34:15.284', NULL, '2020-09-08 13:34:15.292', 10010, '2');
INSERT INTO `judge_solution`
VALUES (29, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543256074.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543256074.out',
        '2020-09-08 13:34:26.171', NULL, '2020-09-08 13:34:26.180', 10010, '12');
INSERT INTO `judge_solution`
VALUES (30, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543267123.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543267123.out',
        '2020-09-08 13:34:35.116', NULL, '2020-09-08 13:34:35.123', 10010, '8');
INSERT INTO `judge_solution`
VALUES (31, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543275817.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543275817.out',
        '2020-09-08 13:34:43.535', NULL, '2020-09-08 13:34:43.544', 10010, '11');
INSERT INTO `judge_solution`
VALUES (32, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543284401.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543284401.out',
        '2020-09-08 13:34:52.824', NULL, '2020-09-08 13:34:52.831', 10010, '12');
INSERT INTO `judge_solution`
VALUES (33, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543295151.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543295151.out',
        '2020-09-08 13:35:02.714', NULL, '2020-09-08 13:35:02.722', 10010, '13');
INSERT INTO `judge_solution`
VALUES (34, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543309106.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543309106.out',
        '2020-09-08 13:35:18.568', NULL, '2020-09-08 13:35:18.576', 10010, '13');
INSERT INTO `judge_solution`
VALUES (35, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543322891.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543322891.out',
        '2020-09-08 13:35:34.739', NULL, '2020-09-08 13:35:34.746', 10008, '2');
INSERT INTO `judge_solution`
VALUES (36, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543335710.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543335710.out',
        '2020-09-08 13:35:44.439', NULL, '2020-09-08 13:35:44.446', 10008, '23');
INSERT INTO `judge_solution`
VALUES (37, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543345471.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543345471.out',
        '2020-09-08 13:35:55.683', NULL, '2020-09-08 13:35:55.691', 10008, '24');
INSERT INTO `judge_solution`
VALUES (38, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543357050.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543357050.out',
        '2020-09-08 13:36:06.800', NULL, '2020-09-08 13:36:06.809', 10008, '24');
INSERT INTO `judge_solution`
VALUES (39, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543367333.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543367333.out',
        '2020-09-08 13:36:17.484', NULL, '2020-09-08 13:36:17.493', 10008, '25');
INSERT INTO `judge_solution`
VALUES (40, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543378504.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543378504.out',
        '2020-09-08 13:36:28.925', NULL, '2020-09-08 13:36:28.942', 10008, '26');
INSERT INTO `judge_solution`
VALUES (41, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543412452.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543412452.out',
        '2020-09-08 13:37:02.536', NULL, '2020-09-08 13:37:02.544', 10009, '1');
INSERT INTO `judge_solution`
VALUES (42, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543423634.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543423634.out',
        '2020-09-08 13:37:10.771', NULL, '2020-09-08 13:37:10.779', 10009, '12');
INSERT INTO `judge_solution`
VALUES (43, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543432647.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543432647.out',
        '2020-09-08 13:37:19.456', NULL, '2020-09-08 13:37:19.466', 10009, '12');
INSERT INTO `judge_solution`
VALUES (44, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543440491.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543440491.out',
        '2020-09-08 13:37:26.692', NULL, '2020-09-08 13:37:26.702', 10009, '16');
INSERT INTO `judge_solution`
VALUES (45, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543447376.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543447376.out',
        '2020-09-08 13:37:34.329', NULL, '2020-09-08 13:37:34.338', 10009, '17');
INSERT INTO `judge_solution`
VALUES (46, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543455768.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543455768.out',
        '2020-09-08 13:37:45.563', NULL, '2020-09-08 13:37:45.571', 10009, '18');
INSERT INTO `judge_solution`
VALUES (47, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543467588.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543467588.out',
        '2020-09-08 13:37:55.781', NULL, '2020-09-08 13:37:55.789', 10009, '15');
INSERT INTO `judge_solution`
VALUES (48, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543694950.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543694950.out',
        '2020-09-08 13:41:44.239', NULL, '2020-09-08 13:41:44.247', 10000, '0');
INSERT INTO `judge_solution`
VALUES (49, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543704873.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543704873.out',
        '2020-09-08 13:41:50.548', NULL, '2020-09-08 13:41:50.558', 10000, '1');
INSERT INTO `judge_solution`
VALUES (50, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543716067.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543716067.out',
        '2020-09-08 13:42:02.805', NULL, '2020-09-08 13:42:02.815', 10001, '0');
INSERT INTO `judge_solution`
VALUES (51, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543778639.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543778639.out',
        '2020-09-08 13:43:06.039', NULL, '2020-09-08 13:43:06.045', 10005, '1');
INSERT INTO `judge_solution`
VALUES (52, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543786862.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543786862.out',
        '2020-09-08 13:43:13.791', NULL, '2020-09-08 13:43:13.798', 10005, '2');
INSERT INTO `judge_solution`
VALUES (53, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543794625.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543794625.out',
        '2020-09-08 13:43:21.636', NULL, '2020-09-08 13:43:21.644', 10005, '3');
INSERT INTO `judge_solution`
VALUES (54, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543802284.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543802284.out',
        '2020-09-08 13:43:28.372', NULL, '2020-09-08 13:43:28.381', 10005, '4');
INSERT INTO `judge_solution`
VALUES (55, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543809221.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543809221.out',
        '2020-09-08 13:43:35.018', NULL, '2020-09-08 13:43:35.027', 10005, '2');
INSERT INTO `judge_solution`
VALUES (56, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543815729.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543815729.out',
        '2020-09-08 13:43:44.464', NULL, '2020-09-08 13:43:44.473', 10005, '3');
INSERT INTO `judge_solution`
VALUES (57, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543827405.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543827405.out',
        '2020-09-08 13:43:54.127', NULL, '2020-09-08 13:43:54.135', 10005, '4');
INSERT INTO `judge_solution`
VALUES (58, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543835088.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543835088.out',
        '2020-09-08 13:44:00.782', NULL, '2020-09-08 13:44:00.792', 10005, '4');
INSERT INTO `judge_solution`
VALUES (59, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543841729.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543841729.out',
        '2020-09-08 13:44:07.847', NULL, '2020-09-08 13:44:07.855', 10005, '5');
INSERT INTO `judge_solution`
VALUES (60, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543865851.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543865851.out',
        '2020-09-08 13:44:31.679', NULL, '2020-09-08 13:44:31.689', 10006, '0');
INSERT INTO `judge_solution`
VALUES (61, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543872399.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543872399.out',
        '2020-09-08 13:44:38.049', NULL, '2020-09-08 13:44:38.058', 10006, '1');
INSERT INTO `judge_solution`
VALUES (62, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543878839.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543878839.out',
        '2020-09-08 13:44:45.124', NULL, '2020-09-08 13:44:45.131', 10006, '2');
INSERT INTO `judge_solution`
VALUES (63, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543886141.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543886141.out',
        '2020-09-08 13:44:54.027', NULL, '2020-09-08 13:44:54.036', 10006, '3');
INSERT INTO `judge_solution`
VALUES (64, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543895361.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543895361.out',
        '2020-09-08 13:45:02.116', NULL, '2020-09-08 13:45:02.132', 10006, '4');
INSERT INTO `judge_solution`
VALUES (65, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543902740.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543902740.out',
        '2020-09-08 13:45:09.175', NULL, '2020-09-08 13:45:09.184', 10006, '5');
INSERT INTO `judge_solution`
VALUES (66, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543910799.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543910799.out',
        '2020-09-08 13:45:17.307', NULL, '2020-09-08 13:45:17.316', 10006, '6');
INSERT INTO `judge_solution`
VALUES (67, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543918063.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543918063.out',
        '2020-09-08 13:45:24.853', NULL, '2020-09-08 13:45:24.862', 10006, '7');
INSERT INTO `judge_solution`
VALUES (68, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543925533.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599543925533.out',
        '2020-09-08 13:45:32.329', NULL, '2020-09-08 13:45:32.337', 10006, '8');
INSERT INTO `judge_solution`
VALUES (69, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599544016233.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599544016233.out',
        '2020-09-08 13:47:03.844', NULL, '2020-09-08 13:47:03.853', 10007, '1');
INSERT INTO `judge_solution`
VALUES (70, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599544024474.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599544024474.out',
        '2020-09-08 13:47:12.008', NULL, '2020-09-08 13:47:12.017', 10007, '2');
INSERT INTO `judge_solution`
VALUES (71, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599544033162.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599544033162.out',
        '2020-09-08 13:47:18.583', NULL, '2020-09-08 13:47:18.591', 10007, '3');
INSERT INTO `judge_solution`
VALUES (72, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599544039342.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599544039342.out',
        '2020-09-08 13:47:27.507', NULL, '2020-09-08 13:47:27.515', 10007, '4');
INSERT INTO `judge_solution`
VALUES (73, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599544048371.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599544048371.out',
        '2020-09-08 13:47:35.983', NULL, '2020-09-08 13:47:35.992', 10007, '5');
INSERT INTO `judge_solution`
VALUES (74, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599544056818.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599544056818.out',
        '2020-09-08 13:47:44.438', NULL, '2020-09-08 13:47:44.447', 10007, '6');
INSERT INTO `judge_solution`
VALUES (75, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599544066520.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599544066520.out',
        '2020-09-08 13:47:52.535', NULL, '2020-09-08 13:47:52.545', 10007, '7');
INSERT INTO `judge_solution`
VALUES (76, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599544073709.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599544073709.out',
        '2020-09-08 13:48:00.627', NULL, '2020-09-08 13:48:00.634', 10007, '8');
INSERT INTO `judge_solution`
VALUES (77, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599544082584.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599544082584.out',
        '2020-09-08 13:48:09.185', NULL, '2020-09-08 13:48:09.194', 10007, '9');
INSERT INTO `judge_solution`
VALUES (78, 'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599544090172.in',
        'https://cdn.jsdelivr.net/gh/yuzhanglong/YuJudge-Resource/defaultCases/1599544090172.out',
        '2020-09-08 13:48:16.253', NULL, '2020-09-08 13:48:16.260', 10007, '10');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`
(
    `id`          int(0) UNSIGNED                                                NOT NULL AUTO_INCREMENT,
    `title`       varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NULL DEFAULT NULL,
    `create_time` datetime(0)                                                    NULL DEFAULT CURRENT_TIMESTAMP(0),
    `update_time` datetime(0)                                                    NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
    `delete_time` datetime(0)                                                    NULL DEFAULT NULL,
    `pk_user`     int(0)                                                         NULL DEFAULT NULL,
    `priority`    varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NULL DEFAULT NULL,
    `is_closed`   tinyint(0)                                                     NULL DEFAULT NULL,
    `content`     varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice`
VALUES (1, '编译器版本', '2020-08-26 00:20:00', '2020-09-11 11:45:47', NULL, 10000, 'COMMON', 0,
        'C\n\n```c\n#include <stdio.h>\n\nint main() {\n    int a,b;\n    scanf(\"%d%d\",&a,&b);\n    printf(\"%d\", a+b);\n    return 0;\n}\n```\n\nC++\n\n```cpp\n#include <iostream>\n#include <cstdio>\n\nusing namespace std;\n\nint main() {\n    int a,b;\n    cin >> a >> b;\n    cout << a+b;\n    return 0;\n}\n```\n\nPython3\n\n```cpp\ns = input().split()\nprint(int(s[0]) + int(s[1]))\n```\n\n\nJava\n\n```java\nimport java.io.*;\nimport java.util.*;\npublic class Main {\n    public static void main(String args[]) throws Exception {\n        Scanner cin=new Scanner(System.in);\n        int a = cin.nextInt(), b = cin.nextInt();\n        System.out.println(a+b);\n    }\n}\n```\n### 测试一下公式: \n$$\\int_0^\\infty x^2 dx$$');
INSERT INTO `notice`
VALUES (2, '常见问题及解答', '2020-08-26 00:28:32', '2020-09-11 11:45:49', NULL, 10000, 'IMPORTANT', 0,
        'C\n\n```c\n#include <stdio.h>\n\nint main() {\n    int a,b;\n    scanf(\"%d%d\",&a,&b);\n    printf(\"%d\", a+b);\n    return 0;\n}\n```\n\nC++\n\n```cpp\n#include <iostream>\n#include <cstdio>\n\nusing namespace std;\n\nint main() {\n    int a,b;\n    cin >> a >> b;\n    cout << a+b;\n    return 0;\n}\n```\n\nPython3\n\n```cpp\ns = input().split()\nprint(int(s[0]) + int(s[1]))\n```\n\n\nJava\n\n```java\nimport java.io.*;\nimport java.util.*;\npublic class Main {\n    public static void main(String args[]) throws Exception {\n        Scanner cin=new Scanner(System.in);\n        int a = cin.nextInt(), b = cin.nextInt();\n        System.out.println(a+b);\n    }\n}\n```\n### 测试一下公式: \n$$\\int_0^\\infty x^2 dx$$');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`
(
    `id`          int(0) UNSIGNED                                              NOT NULL AUTO_INCREMENT,
    `create_time` datetime(0)                                                  NULL DEFAULT CURRENT_TIMESTAMP(0),
    `update_time` datetime(0)                                                  NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
    `delete_time` datetime(0)                                                  NULL DEFAULT NULL,
    `name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission`
VALUES (1, '2020-08-30 12:11:17', '2020-08-30 12:13:28', NULL, 'ADMIN', '系统管理员');
INSERT INTO `permission`
VALUES (2, '2020-08-30 12:53:30', '2020-09-08 12:55:12', NULL, 'PROBLEM_MANAGER', '管理题目和题目集');
INSERT INTO `permission`
VALUES (3, '2020-08-30 12:58:38', '2020-09-08 12:55:14', NULL, 'ANY', '无额外权限限制');
INSERT INTO `permission`
VALUES (4, '2020-08-30 13:45:42', '2020-09-08 12:55:16', NULL, 'NOTICE_MANGER', '公告、信息管理');

-- ----------------------------
-- Table structure for problem_set
-- ----------------------------
DROP TABLE IF EXISTS `problem_set`;
CREATE TABLE `problem_set`
(
    `id`               int(0) UNSIGNED                                               NOT NULL AUTO_INCREMENT,
    `name`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `description`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `pk_user`          int(0)                                                        NULL DEFAULT NULL,
    `create_time`      datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0),
    `update_time`      datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
    `delete_time`      datetime(0)                                                   NULL DEFAULT NULL,
    `deadline`         datetime(0)                                                   NULL DEFAULT NULL,
    `start_time`       datetime(0)                                                   NULL DEFAULT NULL,
    `allowed_language` json                                                          NULL,
    `judge_preference` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT 'ACM',
    `time_penalty`     int(0)                                                        NULL DEFAULT 20,
    `is_open`          tinyint(0)                                                    NULL DEFAULT 0,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of problem_set
-- ----------------------------
INSERT INTO `problem_set`
VALUES (100, '测试题目集', '这个题目集供开发者测试使用', 10000, '2020-07-27 10:23:38', '2022-09-08 13:24:19', NULL, '2022-09-07 11:35:55',
        '2020-08-06 18:00:00', '[
    \"C\",
    \"C_PLUS_PLUS\",
    \"PYTHON\",
    \"JAVA\"
  ]', 'ACM', 20, 1);

-- ----------------------------
-- Table structure for problem_set_problem
-- ----------------------------
DROP TABLE IF EXISTS `problem_set_problem`;
CREATE TABLE `problem_set_problem`
(
    `id`             int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
    `pk_problem_set` int(0)          NULL DEFAULT NULL,
    `pk_problem`     int(0)          NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of problem_set_problem
-- ----------------------------
INSERT INTO `problem_set_problem`
VALUES (14, 100, 10000);
INSERT INTO `problem_set_problem`
VALUES (15, 100, 10001);
INSERT INTO `problem_set_problem`
VALUES (16, 100, 10002);
INSERT INTO `problem_set_problem`
VALUES (17, 100, 10003);
INSERT INTO `problem_set_problem`
VALUES (18, 100, 10004);
INSERT INTO `problem_set_problem`
VALUES (19, 100, 10005);
INSERT INTO `problem_set_problem`
VALUES (20, 100, 10006);
INSERT INTO `problem_set_problem`
VALUES (21, 100, 10007);
INSERT INTO `problem_set_problem`
VALUES (22, 100, 10008);
INSERT INTO `problem_set_problem`
VALUES (23, 100, 10009);
INSERT INTO `problem_set_problem`
VALUES (24, 100, 10010);
INSERT INTO `problem_set_problem`
VALUES (25, 100, 10011);
INSERT INTO `problem_set_problem`
VALUES (26, 100, 10012);

-- ----------------------------
-- Table structure for problem_set_user
-- ----------------------------
DROP TABLE IF EXISTS `problem_set_user`;
CREATE TABLE `problem_set_user`
(
    `id`             int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
    `pk_user`        int(0)          NULL DEFAULT NULL COMMENT '排名信息对应的用户ID',
    `pk_problem_set` int(0)          NULL DEFAULT NULL COMMENT '排名信息对应的题目集ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of problem_set_user
-- ----------------------------

-- ----------------------------
-- Table structure for submission
-- ----------------------------
DROP TABLE IF EXISTS `submission`;
CREATE TABLE `submission`
(
    `id`               int(0) UNSIGNED                                               NOT NULL AUTO_INCREMENT,
    `pk_problem`       int(0)                                                        NULL DEFAULT NULL,
    `create_time`      datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0),
    `update_time`      datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
    `delete_time`      datetime(0)                                                   NULL DEFAULT NULL,
    `pk_user`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `language`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '提交语言',
    `judge_condition`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '这个提交的状态',
    `time_cost`        int(0) UNSIGNED                                               NULL DEFAULT NULL COMMENT '花费时间',
    `memory_cost`      int(0) UNSIGNED                                               NULL DEFAULT NULL COMMENT '花费内存',
    `judge_result`     json                                                          NULL COMMENT '判题机输出',
    `code_content`     longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     NULL COMMENT '用户代码',
    `judge_preference` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'ACM' COMMENT '判题偏好',
    `pk_problem_set`   int(0)                                                        NULL DEFAULT NULL COMMENT '对应的题目集',
    `pk_judge_host`    int(0)                                                        NULL DEFAULT NULL COMMENT '处理本次提交的判题机',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`
(
    `id`          int(0) UNSIGNED                                               NOT NULL AUTO_INCREMENT,
    `title`       varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL,
    `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `create_time` datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0),
    `update_time` datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
    `delete_time` datetime(0)                                                   NULL DEFAULT NULL,
    `color`       varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`                int(0) UNSIGNED                                               NOT NULL AUTO_INCREMENT,
    `nickname`          varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '用户昵称',
    `password`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '加密后的用户密码',
    `create_time`       datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0),
    `update_time`       datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
    `delete_time`       datetime(0)                                                   NULL DEFAULT NULL,
    `email`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `avatar`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'http://cdn.yuzzl.top/120200072905619.jpg' COMMENT '用户头像地址',
    `ac_amount`         int(0)                                                        NULL DEFAULT 0,
    `submission_amount` int(0)                                                        NULL DEFAULT 0,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10195
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES (10000, 'Admin', '4fa8d6515821cdbb82631985611f437a', '2020-08-22 13:57:02', '2020-09-11 17:35:39', NULL,
        'yuzl1123@163.com', 'http://cdn.yuzzl.top/120200072905619.jpg', 0, 0);
INSERT INTO `user`
VALUES (10195, 'User', '5f4dcc3b5aa765d61d8327deb882cf99', '2020-09-15 11:31:23', '2020-09-15 11:31:23', NULL, NULL,
        'http://cdn.yuzzl.top/120200072905619.jpg', 0, 0);
INSERT INTO `user`
VALUES (10196, 'Manager', '1d0258c2440a8d19e716292b231e3190', '2020-09-15 11:31:44', '2020-09-15 11:32:17', NULL, NULL,
        'http://cdn.yuzzl.top/120200072905619.jpg', 0, 0);

-- ----------------------------
-- Table structure for user_group
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group`
(
    `id`          int(0) UNSIGNED                                               NOT NULL AUTO_INCREMENT,
    `name`        varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL,
    `create_time` datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0),
    `update_time` datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
    `delete_time` datetime(0)                                                   NULL DEFAULT NULL,
    `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_group
-- ----------------------------
INSERT INTO `user_group`
VALUES (1, 'ROOT', '2020-08-22 10:01:24', '2020-08-22 10:03:35', NULL, '系统管理员');
INSERT INTO `user_group`
VALUES (2, 'COMMON', '2020-08-22 21:42:13', '2020-09-08 12:55:38', NULL, '一般用户');
INSERT INTO `user_group`
VALUES (3, 'PROBLEM_MANAGER', '2020-08-23 10:49:06', '2020-09-08 12:55:40', NULL, '管理题目集、题目');

-- ----------------------------
-- Table structure for user_group_permission
-- ----------------------------
DROP TABLE IF EXISTS `user_group_permission`;
CREATE TABLE `user_group_permission`
(
    `pk_user_group` int(0)          NULL DEFAULT NULL,
    `pk_permission` int(0)          NULL DEFAULT NULL,
    `id`            int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_group_permission
-- ----------------------------
INSERT INTO `user_group_permission`
VALUES (1, 1, 1);

-- ----------------------------
-- Table structure for user_user_group
-- ----------------------------
DROP TABLE IF EXISTS `user_user_group`;
CREATE TABLE `user_user_group`
(
    `id`            int(0) NOT NULL AUTO_INCREMENT,
    `pk_user`       int(0) NULL DEFAULT NULL,
    `pk_user_group` int(0) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_user_group
-- ----------------------------
INSERT INTO `user_user_group`
VALUES (1, 10000, 1);
INSERT INTO `user_user_group`
VALUES (3, 10195, 2);
INSERT INTO `user_user_group`
VALUES (5, 10196, 3);

SET FOREIGN_KEY_CHECKS = 1;