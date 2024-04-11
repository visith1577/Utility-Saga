package utils;

import dev.langchain4j.model.anthropic.AnthropicChatModel;
import dev.langchain4j.model.chat.ChatLanguageModel;
import io.github.cdimascio.dotenv.Dotenv;;

public class ReportGenerator {

    public static final Dotenv dotenv = Dotenv
            .configure()
            .load();

    public String dailyReport(String account_no, int total_units, int target_units, int monthly_budget) {
        ChatLanguageModel model = AnthropicChatModel.withApiKey(dotenv.get("CLAUDE_KEY"));
        String prompt_tmp = String.format("You are an energy analyst tasked with providing an analytic report on electricity consumption and billing for client %s. The report should cover the following:\n" +
                "\n" +
                "1. Daily Consumption Analysis:\n" +
                "   - Cost for the day: Rs.%d\n" +
                "   - Maximum Cost set for the day: Rs.%d\n" +
                "   - Analyze if the daily consumption exceeded the maximum limit and provide insights.\n" +
                "\n" +
                "2. Monthly Consumption Forecast:\n" +
                "   - This month's electricity budget: Rs.%d\n" +
                "   - Based on the daily consumption patterns so far, forecast the total units likely to be consumed this month.\n" +
                "   - Calculate the projected electricity cost for the month based on the forecast and the electricity rate per unit.\n" +
                "   - Highlight if the projected cost is within the monthly budget or likely to exceed it.\n" +
                "\n" +
                "3. Energy-saving Recommendations:\n" +
                "   - Suggest practical tips and strategies to reduce electricity consumption without compromising essential needs.\n" +
                "   - Highlight potential areas where energy efficiency can be improved (e.g., appliances, lighting, HVAC systems).\n" +
                "\n" +
                "4. Output Structure: \n" +
                "   - should be in a JSON format. {Daily Consumption Analysis: content, Monthly Consumption Forecast: content, Energy-saving Recommendations: content}\n" +
                "Please provide a comprehensive report covering the above points, using clear language. The report should help me understand my electricity consumption patterns, identify potential areas of concern, \n" +
                "and take informed actions to optimize energy usage and costs. limit the report to a maximum of 50 words. \n",
        account_no, total_units, target_units, monthly_budget);

        return model.generate(prompt_tmp);
    }
}