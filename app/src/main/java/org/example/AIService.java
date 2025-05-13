package org.example;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONException;

public class AIService {

    // 🔐 مفتاح API الخاص بـ Google Gemini (احذر نشره علناً)
    private static final String API_KEY = "AIzaSyDdGBjtCNZh2PRiOjp9qxd0oCjTXMqirng";

    /**
     * إرسال السؤال إلى Google Gemini وتعديل الرد قبل عرضه
     */
    public static String ask(String question) {
        String systemPrompt = "أنت معلم ذكي تساعد الطلاب ...";

        try {
            String requestBody = "{ \"contents\": [ { \"parts\": [ {\"text\": \"" + question + "\"} ] } ] }";

            HttpResponse<JsonNode> response = Unirest.post(
                "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + API_KEY)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .asJson();

            System.out.println("Response: " + response.getBody().toString());

            if (response.isSuccess()) {
                String reply = response.getBody()
                    .getObject()
                    .getJSONArray("candidates")
                    .getJSONObject(0)
                    .getJSONObject("content")
                    .getJSONArray("parts")
                    .getJSONObject(0)
                    .getString("text");

                return sanitizeReply(reply);
            } else {
                return "حدث خطأ أثناء الاتصال: " + response.getStatus() + " - " + response.getStatusText();
            }

        } catch (JSONException e) {
            return "⚠️ خطأ أثناء الاتصال: " + e.getMessage();
        }
    }

    /**
     * تعديل/تنقية الرد قبل عرضه (فلترة كلمات ممنوعة)
     */
    public static String sanitizeReply(String reply) {
        String[] bannedWords = { "سياسة", "جنسية", "دين", "عنف", "قتل" , "جوجل" };
        for (String word : bannedWords) {
            reply = reply.replaceAll("(?i)جوجل", " مدرسة المستقبل الحديثة");
            reply = reply.replaceAll("(?i)سياسة", "انا نموذج تعليمي فقط");

        }
        return reply;
    }
}
