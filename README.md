## Пример использования 

Отнаследовать DataUpdater и переопределить метод updateData(file) - в нём необходимо обновлять ваши локальные данные. На вход метода - обновлённый файл.
```java
@Component
public class NewDataUpdater extends DataUpdater {

    public NewDataUpdater(DataProcessingService dataProcessingService) {
        super(dataProcessingService);
    }

    @Override
    public void updateData(File file) {
        System.out.println("Consider updating file");
    }
}
```
Не забудьте добавить com.nis в package-scan или поднять бин DataProcessingService руками.
