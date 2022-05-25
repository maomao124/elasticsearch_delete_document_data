package mao.elasticsearch_delete_document_data;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Project name(项目名称)：elasticsearch_delete_document_data
 * Package(包名): mao.elasticsearch_delete_document_data
 * Class(类名): ElasticSearchTest
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/5/25
 * Time(创建时间)： 21:50
 * Version(版本): 1.0
 * Description(描述)： SpringBootTest
 */

@SpringBootTest
public class ElasticSearchTest
{
    private static RestHighLevelClient client;

    @BeforeAll
    static void beforeAll()
    {
        client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));
    }

    /**
     * 同步删除
     *
     * @throws Exception Exception
     */
    @Test
    void delete() throws Exception
    {
        //构建请求
        DeleteRequest deleteRequest = new DeleteRequest("book", "5");
        //发起请求
        DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
        //获取数据
        DocWriteResponse.Result result = deleteResponse.getResult();
        System.out.println(result);
    }

    @Test
    void delete_async() throws Exception
    {
        //构建请求
        DeleteRequest deleteRequest = new DeleteRequest("book", "5");
        //发起异步请求
        client.deleteAsync(deleteRequest, RequestOptions.DEFAULT, new ActionListener<DeleteResponse>()
        {
            @Override
            public void onResponse(DeleteResponse deleteResponse)
            {
                //获取数据
                DocWriteResponse.Result result = deleteResponse.getResult();
                System.out.println(result);
            }

            @Override
            public void onFailure(Exception e)
            {
                e.printStackTrace();
            }
        });

        Thread.sleep(2000);
    }
}
