import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.send.SendAudio
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendVoice
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.Voice
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

class BagalaApp

fun main() {
    val bagalaBot = BagalaBot()
    val telegramBotsApi = TelegramBotsApi(DefaultBotSession::class.java)
    telegramBotsApi.registerBot(bagalaBot)
}

class BagalaBot : TelegramLongPollingBot() {
    override fun getBotUsername(): String = "QSD_Bagala_Bot"

    override fun getBotToken(): String = "1420378677:AAEJSXcxHyADCHhg-eFlBT14zBWyTlHmrjs"

    var moneyEarnedArray = HashMap<String, Int>()
    var whoWantsToWithdrawMoney = HashMap<String, Int>()

    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage()) {
            // юзер запустил бота
            if (update.message.text == "/start") {
                val keyboard = ReplyKeyboardMarkup()
                keyboard.keyboard = listOf(
                        KeyboardRow().apply { add(KeyboardButton("\uD83D\uDCC8 Aýdıomaterıaldy baǵalaý")) },
                        KeyboardRow().apply { add(KeyboardButton("\uD83D\uDC64 Menіń kabınetіm")) },
                        KeyboardRow().apply { add(KeyboardButton("\uD83D\uDCB8 Aqshany alý")) }
                )
                val sendMessage = SendMessage()
                sendMessage.replyMarkup = keyboard
                sendMessage.text = "Sálemetsіz be, ${update.message.chat.firstName} \uD83D\uDC4B \nBotpen jumysty bastaý úshіn mázіrden kerek pýnktі tańdańyz"
                sendMessage.chatId = update.message.chatId.toString()
                execute(sendMessage)
            }

            // юзер выбрал один пункт из главного меню
            if (update.message.text == "\uD83D\uDCC8 Aýdıomaterıaldy baǵalaý") {
//            val sendVoice = SendVoice()
//            val voiceObj = Voice()
//            voiceObj.fileId = "AwACAgIAAxkBAAOZX7ArwzwJ2M0CuUNSlztYlttWarkAAvAIAAJxoEhIQA8I5dE7XwkeBA"
//            voiceObj.fileUniqueId = "AgAD8AgAAnGgSEg"
//            voiceObj.mimeType = "audio/ogg"
//            voiceObj.duration = 10
//            voiceObj.fileSize = 40590
//            sendVoice.voice = voiceObj as InputFile
//            sendVoice.chatId = update.message.chatId.toString()
//            execute(sendVoice)

                val sendMessageTemp = SendMessage()
                sendMessageTemp.chatId = update.message.chatId.toString()
                sendMessageTemp.text = "\"Aýdıomaterıal jіberіldі\""
                execute(sendMessageTemp)

                val sendMessage = SendMessage()
                sendMessage.text = "Bul aýdıony 0-den 5-ke deıіn baǵalańyz\n" +
                        "Baǵalaý krıterıılerі:\n" +
                        "0: Óte nashar dybys\n" +
                        "1: Dym túsіnіksіz\n" +
                        "2: Aýdıo sapysy nashar jáne oqý mánerі nashar\n" +
                        "3: Oqý sapasy jaksy, aýdıo sapasy nashar\n" +
                        "4: Aýdıo sapasy jaqsy, oqýda qateler baıqaldy\n" +
                        "5: Keremet dybys, bárі túsіnіktі"
                sendMessage.chatId = update.message.chatId.toString()
                sendMessage.replyMarkup = InlineKeyboardMarkup().apply {
                    keyboard = listOf(
                            listOf(
                                    InlineKeyboardButton("0️⃣").apply { callbackData = "Rating0" },
                                    InlineKeyboardButton("1️⃣").apply { callbackData = "Rating1" },
                                    InlineKeyboardButton("2️⃣").apply { callbackData = "Rating2" },
                                    InlineKeyboardButton("3️⃣").apply { callbackData = "Rating3" },
                                    InlineKeyboardButton("4️⃣").apply { callbackData = "Rating4" },
                                    InlineKeyboardButton("5️⃣\uD83D\uDD1D").apply { callbackData = "Rating5" },
                            )
                    )
                }
                execute(sendMessage)
            }

            if (update.message.text == "\uD83D\uDC64 Menіń kabınetіm") {
                val sendMessageTemp = SendMessage()
                sendMessageTemp.chatId = update.message.chatId.toString()
                sendMessageTemp.text =
                        "Sіzdіń esіmіńіz: ${update.message.chat.firstName}\n" +
                                "Sіzdіń username: ${update.message.chat.userName}\n" +
                                "Balans: ${moneyEarnedArray[update.message.chatId.toString()]}"
                execute(sendMessageTemp)
            }

            if (update.message.text == "\uD83D\uDCB8 Aqshany alý") {
                val chatId = update.message.chatId.toString()
                whoWantsToWithdrawMoney[chatId] = moneyEarnedArray[chatId] ?: 0
                val sendMessageTemp = SendMessage()
                sendMessageTemp.chatId = update.message.chatId.toString()
                sendMessageTemp.text =
                        "Sіzdіń suranysyńyz óńdelýde!\n" +
                        "chat id: $chatId \n" +
                        "balans: ${moneyEarnedArray[chatId]}"
                execute(sendMessageTemp)
            }
        }

        if (update.hasCallbackQuery()) {
            val chatId = update.callbackQuery.message.chatId.toString()
            if (update.callbackQuery.data == "Rating0") {
                val sendMessage0 = SendMessage()
                sendMessage0.chatId = chatId
                sendMessage0.text = "0 tańdaldy"
                execute(sendMessage0)

                if (moneyEarnedArray[chatId] != null) {
                    moneyEarnedArray[chatId] = moneyEarnedArray[chatId]?.plus(500) ?: 0
                } else {
                    moneyEarnedArray[chatId] = 500
                }


                val sendMessageTemp = SendMessage()
                sendMessageTemp.chatId = chatId
                sendMessageTemp.text = "\"Aýdıomaterıal jіberіldі\""
                execute(sendMessageTemp)

                val sendMessageNew = SendMessage()
                sendMessageNew.text = "Bul aýdıony 0-den 5-ke deıіn baǵalańyz\n" +
                        "Baǵalaý krıterıılerі:\n" +
                        "0: Óte nashar dybys\n" +
                        "1: Dym túsіnіksіz\n" +
                        "2: Aýdıo sapysy nashar jáne oqý mánerі nashar\n" +
                        "3: Oqý sapasy jaksy, aýdıo sapasy nashar\n" +
                        "4: Aýdıo sapasy jaqsy, oqýda qateler baıqaldy\n" +
                        "5: Keremet dybys, bárі túsіnіktі"
                sendMessageNew.chatId = chatId
                sendMessageNew.replyMarkup = InlineKeyboardMarkup().apply {
                    keyboard = listOf(
                            listOf(
                                    InlineKeyboardButton("0️⃣").apply { callbackData = "Rating0" },
                                    InlineKeyboardButton("1️⃣").apply { callbackData = "Rating1" },
                                    InlineKeyboardButton("2️⃣").apply { callbackData = "Rating2" },
                                    InlineKeyboardButton("3️⃣").apply { callbackData = "Rating3" },
                                    InlineKeyboardButton("4️⃣").apply { callbackData = "Rating4" },
                                    InlineKeyboardButton("5️⃣\uD83D\uDD1D").apply { callbackData = "Rating5" },
                            )
                    )
                }
                execute(sendMessageNew)

            }
            if (update.callbackQuery.data == "Rating1") {
                val sendMessage0 = SendMessage()
                sendMessage0.chatId = chatId
                sendMessage0.text = "1 tańdaldy"
                execute(sendMessage0)

                if (moneyEarnedArray[chatId] != null) {
                    moneyEarnedArray[chatId] = moneyEarnedArray[chatId]?.plus(500) ?: 0
                } else {
                    moneyEarnedArray[chatId] = 500
                }

                val sendMessageTemp = SendMessage()
                sendMessageTemp.chatId = chatId
                sendMessageTemp.text = "\"Aýdıomaterıal jіberіldі\""
                execute(sendMessageTemp)

                val sendMessageNew = SendMessage()
                sendMessageNew.text = "Bul aýdıony 0-den 5-ke deıіn baǵalańyz\n" +
                        "Baǵalaý krıterıılerі:\n" +
                        "0: Óte nashar dybys\n" +
                        "1: Dym túsіnіksіz\n" +
                        "2: Aýdıo sapysy nashar jáne oqý mánerі nashar\n" +
                        "3: Oqý sapasy jaksy, aýdıo sapasy nashar\n" +
                        "4: Aýdıo sapasy jaqsy, oqýda qateler baıqaldy\n" +
                        "5: Keremet dybys, bárі túsіnіktі"
                sendMessageNew.chatId = chatId
                sendMessageNew.replyMarkup = InlineKeyboardMarkup().apply {
                    keyboard = listOf(
                            listOf(
                                    InlineKeyboardButton("0️⃣").apply { callbackData = "Rating0" },
                                    InlineKeyboardButton("1️⃣").apply { callbackData = "Rating1" },
                                    InlineKeyboardButton("2️⃣").apply { callbackData = "Rating2" },
                                    InlineKeyboardButton("3️⃣").apply { callbackData = "Rating3" },
                                    InlineKeyboardButton("4️⃣").apply { callbackData = "Rating4" },
                                    InlineKeyboardButton("5️⃣\uD83D\uDD1D").apply { callbackData = "Rating5" },
                            )
                    )
                }
                execute(sendMessageNew)

            }
            if (update.callbackQuery.data == "Rating2") {
                val sendMessage0 = SendMessage()
                sendMessage0.chatId = chatId
                sendMessage0.text = "2 tańdaldy"
                execute(sendMessage0)

                if (moneyEarnedArray[chatId] != null) {
                    moneyEarnedArray[chatId] = moneyEarnedArray[chatId]?.plus(500) ?: 0
                } else {
                    moneyEarnedArray[chatId] = 500
                }

                val sendMessageTemp = SendMessage()
                sendMessageTemp.chatId = chatId
                sendMessageTemp.text = "\"Aýdıomaterıal jіberіldі\""
                execute(sendMessageTemp)

                val sendMessageNew = SendMessage()
                sendMessageNew.text = "Bul aýdıony 0-den 5-ke deıіn baǵalańyz\n" +
                        "Baǵalaý krıterıılerі:\n" +
                        "0: Óte nashar dybys\n" +
                        "1: Dym túsіnіksіz\n" +
                        "2: Aýdıo sapysy nashar jáne oqý mánerі nashar\n" +
                        "3: Oqý sapasy jaksy, aýdıo sapasy nashar\n" +
                        "4: Aýdıo sapasy jaqsy, oqýda qateler baıqaldy\n" +
                        "5: Keremet dybys, bárі túsіnіktі"
                sendMessageNew.chatId = chatId
                sendMessageNew.replyMarkup = InlineKeyboardMarkup().apply {
                    keyboard = listOf(
                            listOf(
                                    InlineKeyboardButton("0️⃣").apply { callbackData = "Rating0" },
                                    InlineKeyboardButton("1️⃣").apply { callbackData = "Rating1" },
                                    InlineKeyboardButton("2️⃣").apply { callbackData = "Rating2" },
                                    InlineKeyboardButton("3️⃣").apply { callbackData = "Rating3" },
                                    InlineKeyboardButton("4️⃣").apply { callbackData = "Rating4" },
                                    InlineKeyboardButton("5️⃣\uD83D\uDD1D").apply { callbackData = "Rating5" },
                            )
                    )
                }
                execute(sendMessageNew)

            }
            if (update.callbackQuery.data == "Rating3") {
                val sendMessage0 = SendMessage()
                sendMessage0.chatId = chatId
                sendMessage0.text = "3 tańdaldy"
                execute(sendMessage0)

                if (moneyEarnedArray[chatId] != null) {
                    moneyEarnedArray[chatId] = moneyEarnedArray[chatId]?.plus(500) ?: 0
                } else {
                    moneyEarnedArray[chatId] = 500
                }

                val sendMessageTemp = SendMessage()
                sendMessageTemp.chatId = chatId
                sendMessageTemp.text = "\"Aýdıomaterıal jіberіldі\""
                execute(sendMessageTemp)

                val sendMessageNew = SendMessage()
                sendMessageNew.text = "Bul aýdıony 0-den 5-ke deıіn baǵalańyz\n" +
                        "Baǵalaý krıterıılerі:\n" +
                        "0: Óte nashar dybys\n" +
                        "1: Dym túsіnіksіz\n" +
                        "2: Aýdıo sapysy nashar jáne oqý mánerі nashar\n" +
                        "3: Oqý sapasy jaksy, aýdıo sapasy nashar\n" +
                        "4: Aýdıo sapasy jaqsy, oqýda qateler baıqaldy\n" +
                        "5: Keremet dybys, bárі túsіnіktі"
                sendMessageNew.chatId = chatId
                sendMessageNew.replyMarkup = InlineKeyboardMarkup().apply {
                    keyboard = listOf(
                            listOf(
                                    InlineKeyboardButton("0️⃣").apply { callbackData = "Rating0" },
                                    InlineKeyboardButton("1️⃣").apply { callbackData = "Rating1" },
                                    InlineKeyboardButton("2️⃣").apply { callbackData = "Rating2" },
                                    InlineKeyboardButton("3️⃣").apply { callbackData = "Rating3" },
                                    InlineKeyboardButton("4️⃣").apply { callbackData = "Rating4" },
                                    InlineKeyboardButton("5️⃣\uD83D\uDD1D").apply { callbackData = "Rating5" },
                            )
                    )
                }
                execute(sendMessageNew)

            }
            if (update.callbackQuery.data == "Rating4") {
                val sendMessage0 = SendMessage()
                sendMessage0.chatId = chatId
                sendMessage0.text = "4 tańdaldy"
                execute(sendMessage0)

                if (moneyEarnedArray[chatId] != null) {
                    moneyEarnedArray[chatId] = moneyEarnedArray[chatId]?.plus(500) ?: 0
                } else {
                    moneyEarnedArray[chatId] = 500
                }

                val sendMessageTemp = SendMessage()
                sendMessageTemp.chatId = chatId
                sendMessageTemp.text = "\"Aýdıomaterıal jіberіldі\""
                execute(sendMessageTemp)

                val sendMessageNew = SendMessage()
                sendMessageNew.text = "Bul aýdıony 0-den 5-ke deıіn baǵalańyz\n" +
                        "Baǵalaý krıterıılerі:\n" +
                        "0: Óte nashar dybys\n" +
                        "1: Dym túsіnіksіz\n" +
                        "2: Aýdıo sapysy nashar jáne oqý mánerі nashar\n" +
                        "3: Oqý sapasy jaksy, aýdıo sapasy nashar\n" +
                        "4: Aýdıo sapasy jaqsy, oqýda qateler baıqaldy\n" +
                        "5: Keremet dybys, bárі túsіnіktі"
                sendMessageNew.chatId = chatId
                sendMessageNew.replyMarkup = InlineKeyboardMarkup().apply {
                    keyboard = listOf(
                            listOf(
                                    InlineKeyboardButton("0️⃣").apply { callbackData = "Rating0" },
                                    InlineKeyboardButton("1️⃣").apply { callbackData = "Rating1" },
                                    InlineKeyboardButton("2️⃣").apply { callbackData = "Rating2" },
                                    InlineKeyboardButton("3️⃣").apply { callbackData = "Rating3" },
                                    InlineKeyboardButton("4️⃣").apply { callbackData = "Rating4" },
                                    InlineKeyboardButton("5️⃣\uD83D\uDD1D").apply { callbackData = "Rating5" },
                            )
                    )
                }
                execute(sendMessageNew)

            }
            if (update.callbackQuery.data == "Rating5") {
                val sendMessage0 = SendMessage()
                sendMessage0.chatId = chatId
                sendMessage0.text = "5 tańdaldy"
                execute(sendMessage0)

                if (moneyEarnedArray[chatId] != null) {
                    moneyEarnedArray[chatId] = moneyEarnedArray[chatId]?.plus(500) ?: 0
                } else {
                    moneyEarnedArray[chatId] = 500
                }

                val sendMessageTemp = SendMessage()
                sendMessageTemp.chatId = chatId
                sendMessageTemp.text = "\"Aýdıomaterıal jіberіldі\""
                execute(sendMessageTemp)

                val sendMessageNew = SendMessage()
                sendMessageNew.text = "Bul aýdıony 0-den 5-ke deıіn baǵalańyz\n" +
                        "Baǵalaý krıterıılerі:\n" +
                        "0: Óte nashar dybys\n" +
                        "1: Dym túsіnіksіz\n" +
                        "2: Aýdıo sapysy nashar jáne oqý mánerі nashar\n" +
                        "3: Oqý sapasy jaksy, aýdıo sapasy nashar\n" +
                        "4: Aýdıo sapasy jaqsy, oqýda qateler baıqaldy\n" +
                        "5: Keremet dybys, bárі túsіnіktі"
                sendMessageNew.chatId = chatId
                sendMessageNew.replyMarkup = InlineKeyboardMarkup().apply {
                    keyboard = listOf(
                            listOf(
                                    InlineKeyboardButton("0️⃣").apply { callbackData = "Rating0" },
                                    InlineKeyboardButton("1️⃣").apply { callbackData = "Rating1" },
                                    InlineKeyboardButton("2️⃣").apply { callbackData = "Rating2" },
                                    InlineKeyboardButton("3️⃣").apply { callbackData = "Rating3" },
                                    InlineKeyboardButton("4️⃣").apply { callbackData = "Rating4" },
                                    InlineKeyboardButton("5️⃣\uD83D\uDD1D").apply { callbackData = "Rating5" },
                            )
                    )
                }
                execute(sendMessageNew)

            }
        }
    }
}
