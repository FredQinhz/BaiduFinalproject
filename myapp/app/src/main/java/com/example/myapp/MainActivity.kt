package com.example.myapp

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp.database.*
import com.example.myapp.fragment.*


class MainActivity : AppCompatActivity() {
    private var main_home_btn: Button? = null
    private var main_video_btn: Button? = null
    private var main_user_btn: Button? = null
    private var bundle: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bundle = this.intent.extras

        main_home_btn = findViewById(R.id.main_home_btn)
        main_video_btn = findViewById(R.id.main_video_btn)
        main_user_btn = findViewById(R.id.main_user_btn)

        val frag = intent.getStringExtra("key")
        if(frag != null){
            when(frag){
                "home" -> {
                    turn_frag(findViewById(R.id.main_home_btn))
                }
                "video" -> {
                    turn_frag(findViewById(R.id.main_video_btn))
                }
                "user" -> {
                    turn_frag(findViewById(R.id.main_user_btn))
                }
                else ->{
                    turn_frag(findViewById(R.id.main_home_btn))
                }
            }
        }
        else{
            turn_frag(findViewById(R.id.main_home_btn))
        }


        //三个主键的点击事件
        main_home_btn?.setOnClickListener(View.OnClickListener {
            turn_frag(findViewById(R.id.main_home_btn))
        })
        main_video_btn?.setOnClickListener(View.OnClickListener {
            turn_frag(findViewById(R.id.main_video_btn))
        })
        main_user_btn?.setOnClickListener(View.OnClickListener {
            turn_frag(findViewById(R.id.main_user_btn))
        })

        // 访问数据库，先实例化 MyDBHelper 的子类
        val dbhelper = MyDataBase(this)
        // 以写入模式获取数据存储库
        val db: SQLiteDatabase = dbhelper.writableDatabase

//        // 清除数据库
//        db.delete("Accounts",null,null)
//        db.delete("News1",null,null)
//        db.delete("News2",null,null)

        // 查找当前注册的用户数量
        var cursor = db.query("Accounts", null, null, null, null, null, null)
        var i = 0
        with(cursor) {
            while (moveToNext()) {
                i++
            }
        }
        if(i == 0){
            db.delete("Accounts",null,null)
            val values = ContentValues()
            values.put("username","fred")
            values.put("password","123456")
            values.put("age", "18")
            values.put("phone", "18878315329")
            values.put("sex", "man")
            db.insert("Accounts", null, values)
            Toast.makeText(this, "已注册用户数： 1, 默认用户为fred", Toast.LENGTH_SHORT).show() // 首次进入程序提示
        }else
            Toast.makeText(this, "已注册用户数： $i", Toast.LENGTH_SHORT).show()

//-------------------------------------------------------------------------------------------------------------------------------------

        cursor = db.query("News1", null, null, null, null, null, null)
        i = 0
        with(cursor) {
            while (moveToNext()) {
                i++
            }
        }
        if(i == 0){
            db.delete("News1",null,null)
            val values = ContentValues()
            values.put("title","微粒贷的风险和危险你知道吗？你还敢强开？")
            values.put("type","置顶")
            values.put("author","康波财经")
            values.put("abstract", "    微粒贷新闻摘要：最新研究发现，微粒贷新闻摘要微粒贷新闻摘要微粒贷新闻摘要微粒贷新闻摘要微粒贷新闻摘要微粒贷新闻摘要微粒贷新闻摘要微粒贷新闻摘要微粒贷新闻摘要微粒贷新闻摘要。微粒贷新闻摘要")
            values.put("article", "    微粒贷新闻正文：微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文，微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文。微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文。\n    微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文，微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文。微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文。\n    微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文，微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文，微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文。\n    微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文，微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文。微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文，微粒贷新闻正文微粒贷新闻正文。\n    微粒贷，新闻正文微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文，微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文微粒贷新闻。正文微粒贷新闻正文微粒贷新闻正文，微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文微粒贷新闻正文。\n。")
            db.insert("News1", null, values)

            values.clear()
            values.put("title","这些重大问题，习近平的回答掷地有声")
            values.put("type","置顶")
            values.put("author","新华社新媒体")
            values.put("abstract", "    【编前语】4月1日出版的第7期《求是》杂志发表习近平总书记的重要文章《关于坚持和发展中国特色社会主义的几个问题》。这篇重要文章中，习近平回答了一系列根本性、原则性、方向性的重大问题，掷地有声。新华社《学习进行时》为您梳理习近平的重要论述。")
            values.put("article", "    只有社会主义才能救中国，只有中国特色社会主义才能发展中国，这是历史的结论、人民的选择。\n" +
                    "　　近些年来，国内外有些舆论提出中国现在搞的究竟还是不是社会主义的疑问，有人说是“资本社会主义”，还有人干脆说是“国家资本主义”、“新官僚资本主义”。这些都是完全错误的。\n" +
                    "　　我们说中国特色社会主义是社会主义，那就是不论怎么改革、怎么开放，我们都始终要坚持中国特色社会主义道路、中国特色社会主义理论体系、中国特色社会主义制度，坚持党的十八大提出的夺取中国特色社会主义新胜利的基本要求。\n" +
                    "　　过去不能搞全盘苏化，现在也不能搞全盘西化或者其他什么化。\n" +
                    "　　如果没有1978年我们党果断决定实行改革开放，并坚定不移推进改革开放，坚定不移把握改革开放的正确方向，社会主义中国就不可能有今天这样的大好局面，就可能面临严重危机，就可能遇到像苏联、东欧国家那样的亡党亡国危机。\n" +
                    "　　如果没有1949年建立新中国并进行社会主义革命和建设，积累了重要的思想、物质、制度条件，积累了正反两方面经验，改革开放也很难顺利推进。\n" +
                    "　　对改革开放前的历史时期要正确评价，不能用改革开放后的历史时期否定改革开放前的历史时期，也不能用改革开放前的历史时期否定改革开放后的历史时期。\n" +
                    "　　我们对社会主义的认识，对中国特色社会主义规律的把握，已经达到了一个前所未有的新的高度，这一点不容置疑。同时，也要看到，我国社会主义还处在初级阶段，我们还面临很多没有弄清楚的问题和待解的难题，对许多重大问题的认识和处理都还处在不断深化的过程之中，这一点也不容置疑。\n" +
                    "　　我们过去取得的实践和理论成果，能够帮助我们更好面对和解决前进中的问题，但不能成为我们骄傲自满的理由，更不能成为我们继续前进的包袱。我们的事业越前进、越发展，新情况新问题就会越多，面临的风险和挑战就会越多，面对的不可预料的事情就会越多。\n" +
                    "　　必须认识到，我们现在的努力以及将来多少代人的持续努力，都是朝着最终实现共产主义这个大目标前进的。同时，必须认识到，实现共产主义是一个非常漫长的历史过程，我们必须立足党在现阶段的奋斗目标，脚踏实地推进我们的事业。\n" +
                    "　　中国特色社会主义是党的最高纲领和基本纲领的统一。中国特色社会主义的基本纲领，概言之，就是建立富强民主文明和谐的社会主义现代化国家。这既是从我国正处于并将长期处于社会主义初级阶段的基本国情出发的，也没有脱离党的最高理想。\n" +
                    "　　事实一再告诉我们，马克思、恩格斯关于资本主义社会基本矛盾的分析没有过时，关于资本主义必然消亡、社会主义必然胜利的历史唯物主义观点也没有过时。这是社会历史发展不可逆转的总趋势，但道路是曲折的。资本主义最终消亡、社会主义最终胜利，必然是一个很长的历史过程。\n" +
                    "　　我们要深刻认识资本主义社会的自我调节能力，充分估计到西方发达国家在经济科技军事方面长期占据优势的客观现实，认真做好两种社会制度长期合作和斗争的各方面准备。\n" +
                    "　　在相当长时期内，初级阶段的社会主义还必须同生产力更发达的资本主义长期合作和斗争，还必须认真学习和借鉴资本主义创造的有益文明成果，甚至必须面对被人们用西方发达国家的长处来比较我国社会主义发展中的不足并加以指责的现实。\n" +
                    "　　我们必须有很强大的战略定力，坚决抵制抛弃社会主义的各种错误主张，自觉纠正超越阶段的错误观念。最重要的，还是要集中精力办好自己的事情，不断壮大我们的综合国力，不断改善我们人民的生活，不断建设对资本主义具有优越性的社会主义，不断为我们赢得主动、赢得优势、赢得未来打下更加坚实的基础。\n")
            db.insert("News1", null, values)

            values.clear()
            values.put("title","习近平会见”元老会“代表团")
            values.put("type","置顶")
            values.put("author","央视网新闻")
            values.put("abstract", "    新华社北京4月1日电 国家主席习近平1日在人民大会堂会见“元老会”代表团。")
            values.put("article", "    习近平指出，世界正处于百年未有之大变局。国际合作的前景、全球性挑战的出路、人类社会的未来，引起越来越多有识之士的思考。我们主张，要树立人类命运共同体意识，建设好、呵护好人类共有的地球家园。对人类长远未来，各国都承担着一份责任。中国是一个发展中大国，但我们不回避应尽的国际责任。中国谋求合作共赢，在实现自身发展的同时帮助广大发展中国家谋求共同进步。中国始终奉行正确义利观，在自身还很贫穷的时候就给予了非洲国家无私帮助。我们提出“一带一路”合作倡议，就是为了与各国实现互利共赢。\n" +
                    "    习近平指出，当前单边主义的冲击让许多国家都感到压力和担忧。但是，国际社会支持多边主义的声音仍然是主导力量。要确保既有多边进程不停顿，已经取得的成果不倒退。中国继续致力于维护国际核不扩散体系、加强应对气候变化国际合作等多边议程。中国对多边机构改革一向持开放态度，主张由各国共同商量，按照公认的规则，照顾各方的合理关切，并且符合绝大多数国家的共同利益。\n" +
                    "    习近平强调，中国提出“一带一路”倡议，是对多边主义和国际合作的重要贡献。5年多来，中国同各国秉持共商共建共享理念，探索合作思路，创新合作模式，丰富了新形势下多边主义的实践。\n" +
                    "    习近平强调，大国关系事关全球战略稳定，大国肩上都承担着特殊责任。中国致力于推进大国协调合作，期待大国和睦相处，不冲突不对抗、相互尊重、合作共赢。中美关系是全世界最重要的双边关系之一。希望美方同中方相向而行，管控分歧，拓展合作，共同发展以协商、合作、稳定为基调的中美关系，为世界提供更多稳定性和可预期性。\n" +
                    "    习近平强调，今年是中华人民共和国成立70周年。坚持中国共产党领导，是中国人民的历史选择。70年来，中国共产党领导中国人民持续奋斗，各方面都取得了巨大成就。同时，我们坚持不断完善自己的发展道路，并正为实现“两个一百年”奋斗目标而不懈努力。中国把自己的事情办好了，对世界而言就是贡献。\n" +
                    "    “元老会”主席、爱尔兰前总统罗宾逊，“元老会”副主席、联合国前秘书长、博鳌亚洲论坛理事长潘基文，墨西哥前总统赛迪略代表“元老会”代表团先后发言。他们首先祝贺中华人民共和国成立70周年以及中国经济社会发展取得的巨大成就，高度评价中方的环保、脱贫和民族工作。他们表示，在多边主义面临挑战的严峻时刻，中国发挥着负责任的建设性作用。中国始终坚持《联合国宪章》的宗旨和原则，积极帮助非洲的和平与发展事业。习近平主席在国际场合的一系列重要讲话向国际社会发出了积极信息。我们要坚定维护以联合国为核心的多边体系，加强在国际和地区安全、应对气候变化等领域的多边合作，期待着中国继续发挥领导作用。\n" +
                    "    杨洁篪、王毅等参加会见。\n" +
                    "    “元老会”由南非前总统曼德拉倡议于2007年7月成立，由各国前政要和重要国际组织前负责人组成，宗旨是推动对话解决冲突、贫困、疾病等全球性问题。（原题为《习近平会见“元老会”代表团》）\n")
            db.insert("News1", null, values)

            values.clear()
            values.put("title","国家发改委等部门印发《关于促进汽车消费的若干措施》的通知")
            values.put("type","置顶")
            values.put("author","新浪新闻")
            values.put("abstract", "    各省、自治区、直辖市及计划单列市、新疆生产建设兵团有关部门和单位：\n" +
                    "    为进一步稳定和扩大汽车消费，促进消费持续恢复，国家发展改革委会同有关部门和单位研究制定《关于促进汽车消费的若干措施》。现印发给你们，请结合实际，认真抓好贯彻落实。")
            values.put("article", "    《措施》指出，加强新能源汽车配套设施建设。落实构建高质量充电基础 设施体系、支持新能源汽车下乡等政策措施。加快乡县、高速公路 和居住区等场景充电基础设施建设，引导用户广泛参与智能有序充 电和车网互动，鼓励开展新能源汽车与电网互动应用试点示范工 作。持续推动换电基础设施相关标准制定，增强兼容性、通用性。加快换电模式推广应用，积极开展公共领域车辆换电模式试点，支 持城市公交场站充换电基础设施建设。鼓励有条件的城市和高速公 路等交通干线加快推进换电站建设。\n" +
                    "    《措施》强调，着力提升农村电网承载能力。合理提高乡村电网改造升级 的投入力度，确保供电可靠性指标稳步提升。进一步加快配电网增 容提质，提高乡村入户电压稳定性，确保农村地区电动汽车安全平 稳充电。\n" +
                    "    《措施》明确，降低新能源汽车购置使用成本。落实延续和优化新能源汽 车车辆购置税减免的政策措施。推动居民小区内的公共充换电设施 用电实行居民电价，推动对执行工商业电价的充换电设施用电实行 峰谷分时电价政策。推动提供充电桩单独装表立户服务，更好满足 居民需要。鼓励充换电设施运营商阶段性降低充电服务费，鼓励地 方对城市公交车辆充电给予优惠。到 2030 年前，对实行两部制电 价的集中式充换电设施用电免收需量（容量）电费。\n" +
                    "    《措施》同时指出，推动公共领域增加新能源汽车采购数量。支持适宜地区的 机关公务、公交、出租、邮政、环卫、园林等公共领域新增或更新 车辆原则上采购新能源汽车，鼓励农村客货邮融合适配车辆更新为 新能源汽车，新能源汽车采购占比逐年提高。")
            db.insert("News1", null, values)
        }

//-------------------------------------------------------------------------------------------------------------------------------------

        cursor = db.query("News2", null, null, null, null, null, null)
        i = 0
        with(cursor) {
            while (moveToNext()) {
                i++
            }
        }
        if(i == 0){
            db.delete("News2",null,null)
            val values = ContentValues()
            values.put("title","美媒：五角大楼盯上谷歌在华AI中心 谷歌忙安抚")
            values.put("type","热点")
            values.put("author","参考消息")
            values.put("abstract", "    参考消息网3月28日报道 美媒称，谷歌公司老板27日与美军高级官员会面，谈话重点可能是谷歌在中国的发展。")
            values.put("article", "    据彭博新闻社网站3月27日报道，据知情人士透露，谷歌公司首席执行官孙达尔·皮柴将在华盛顿与参谋长联席会议主席约瑟夫·邓福德将军会面。谷歌公司发出面谈邀请起因于邓福德对谷歌在中国开发人工智能（AI）提出批评。\n" +
                    "　　美国国防部发言人也证实邓福德计划在本周会见一位谷歌高管，但拒绝提供进一步细节。\n" +
                    "　　报道称，依赖人工智能实力而开展的云计算业务是谷歌公司除广告以外未来收入增长的主要来源。五角大楼和中国都是潜在大买家。\n" +
                    "　　谷歌在云业务方面的主要竞争对手亚马逊公司和微软公司都在积极讨好这两个买家。但谷歌没有。在过去一年里，该公司终止了与五角大楼的人工智能合同，同时在中国寻找新业务。\n" +
                    "　　报道称，2017年底，谷歌宣布在北京成立人工智能中心。跟公司的许多决策一样，这也是出于希望聘用最出色的程序员。谷歌在过去五年里一直在向投资者和公众表明，它是世界顶尖人工智能公司，但它需要有才华的工程师来保持这一领先地位。\n" +
                    "　　报道指出，人工智能领域的技术人员格外稀少且待遇不菲，其中有很多都在中国。\n" +
                    "　　报道称，去年，亚马逊和微软也宣布在中国设立自己的人工智能实验室。与谷歌不同，这两家公司已经在中国销售云服务。亚马逊和微软还都谋求与美国军方达成云服务交易。")
            values.put("image",R.drawable.pentagon)
            db.insert("News2", null, values)

            values.clear()
            values.put("title","蔡英文财产曝光：存款5406万 名下拥有6笔不动产")
            values.put("type","热点")
            values.put("author","人民日报海外网")
            values.put("abstract", "    　中国台湾网3月29日讯 据台湾“东森新闻云”报道，台“监察院”29日出版最新一期“廉政专刊”，其中台湾地区领导人蔡英文去年11月申报的存款共5406万872元（新台币，下同，约1181万人民币），名下还有4笔土地、2笔建筑物，债务的部分较去年减少85万元。")
            values.put("article", "    根据最新一期“廉政专刊”报道，蔡英文存款申报5406万872元，较去年申报的资料增加187万余元；持有公司股票总额413万6370元，则和去年申报一致。\n" +
                    "\n" +
                    "　　另外，债务部分，去年3月19日蔡英文新增一笔向台湾华南银行的短期授信贷款，至去年11月余额仍有20万7009元，再加上申报的房贷余额787万1294元，共计807万8303元，但较前次申报房贷893万4710元，减少85万6407元。\n" +
                    "\n" +
                    "　　蔡英文申报土地4笔、建筑物2笔，都与前次申报相同，土地包括台北市松山区2笔、大安区1笔、新北市永和区1笔；建筑物则是台北市大安区1笔、新北永和区1笔。她另申报有价证券413万6370元，都是她家族企业正中企业的股票。\n" +
                    "\n" +
                    "　　还有，蔡英文申报著作权财产《洋葱炒蛋到小英便当》《英派：点亮台湾的这一里路》，均为无法估价。她将土地租给家族经营的东道公司，每月租金9万元，她本身也投资东道公司110万元。\n")
            values.put("image",R.drawable.cyw)
            db.insert("News2", null, values)

            values.clear()
            values.put("title","苹果Mac状况频出：硬件软件都出了问题")
            values.put("type","热点")
            values.put("author","IT168")
            values.put("abstract", "    新闻摘要：最新研究发现，天文学家使用先进的望远镜和探测器，在距离地球数光年的神秘行星上发现了可能存在生命的迹象。这一重大发现给了人类寻找外星生命的希望。[这是一段测试文本]")
            values.put("article", "    新闻正文：自从几十年前开始研究宇宙以来，科学家们一直在探索外太空是否存在其他生命形式。最近，一项令人兴奋的发现让人们对这个问题更加乐观。\n    由一支国际性的科学团队组成的研究小组使用了最先进的望远镜和探测器，在一颗距离地球数光年的神秘行星上观测到了一系列令人惊讶的迹象。这些迹象包括大气中的氧气丰度异常和地表上出现的复杂有机分子。这些发现为该行星上存在生命的可能性提供了有力的证据。\n    科学家们对这颗行星进行了详细的研究，并排除了其他解释。他们确定大气中的氧气含量远超出自然界非生物过程所能解释的范围，这意味着某种生物活动可能在这颗行星上产生了氧气。此外，地表上发现的复杂有机分子也显示了类似生命存在的迹象。\n    虽然科学家们无法直接观察到这些生命体或获取生物样本，但他们认为这是一个重要的突破。这一发现引发了全球范围内的热议，并将进一步推动太空探索和寻找外星生命的研究。\n    未来，科学家们计划使用更先进的技术和设备来进一步研究这颗神秘行星，并希望能确认是否存在复杂的生命形式。这一发现将对人类的理解和认识宇宙产生深远影响，也将激发更多的科学家投身于寻找外太空生命的研究中。\n    [这是测试所用的虚构新闻，其中的数据和事实并不真实存在]。")
            values.put("image",R.drawable.apple)
            db.insert("News2", null, values)
        }
        cursor.close()

        db.close()
    }


    fun turn_frag(b: Button) {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        val f_h = Home_frag()
        val f_v = Video_frag()
        val f_u = User_frag()
        val home_button = findViewById<Button>(R.id.main_home_btn)
        val video_button = findViewById<Button>(R.id.main_video_btn)
        val user_button = findViewById<Button>(R.id.main_user_btn)
        when (b.id) {
            R.id.main_home_btn -> {
                home_button.setBackgroundResource(R.drawable.main_on)
                video_button.setBackgroundResource(R.drawable.main_video_off)
                user_button.setBackgroundResource(R.drawable.main_user_off)
                ft.replace(R.id.frag, f_h)
            }
            R.id.main_video_btn -> {
                home_button.setBackgroundResource(R.drawable.main_off)
                video_button.setBackgroundResource(R.drawable.main_video_on)
                user_button.setBackgroundResource(R.drawable.main_user_off)
                ft.replace(R.id.frag, f_v)
            }
            R.id.main_user_btn -> {
                home_button.setBackgroundResource(R.drawable.main_off)
                video_button.setBackgroundResource(R.drawable.main_video_off)
                user_button.setBackgroundResource(R.drawable.main_user_on)
                ft.replace(R.id.frag, f_u)
            }
        }
        ft.commit()
    }
    companion object {
        var username: String? = null
    }
}