1. @EnableAutoConfiguration

2. AutoConfigurationImportSelector与Spring的整合
   1. spring boot使用了spring提供的BeanDefinitionRegistryPostProcessor扩展点，并实现了ConfigurationClassPostProcessor类，从而实现了spring之上的一系列逻辑扩展。
   
3. Spring Boot中的SPI机制

   在META-INF/spring.factories文件中配置接口的实现类名称，然后再程序中读取这些配置文件并实例化。这种自定义的SPI机制是Spring Boot Starter实现的基础。

   ```java
   // spring-data-redis中META-INF/spring.factories中的内容
   org.springframework.data.repository.core.support.RepositoryFactorySupport=org.springframework.data.redis.repository.support.RedisRepositoryFactory
   ```

   1. 实现原理

      spring-core包中定义了`SpringFactoriesLoader`类，这个类实现了检索META-INF/spring.factories文件，并获取指定接口的配置的功能。

      ```java
      public final class SpringFactoriesLoader {
      
      	/**
      	 * 定义了检索的位置
      	 * The location to look for factories.
      	 * <p>Can be present in multiple JAR files.
      	 */
      	public static final String FACTORIES_RESOURCE_LOCATION = "META-INF/spring.factories";
      
      
      	private static final Log logger = LogFactory.getLog(SpringFactoriesLoader.class);
      
      	private static final Map<ClassLoader, MultiValueMap<String, String>> cache = new ConcurrentReferenceHashMap<>();
      
      
      	private SpringFactoriesLoader() {
      	}
      
      
      	/**
      	 * 根据接口类型获取其实现类的实例，返回的是接口实现类的列表
      	 * 比如想要a.b.Interface类型的实例，则在当前模块所有jar包中的META- INF/spring.factories中扫描a.b.Interface类型的实现类对象
      	 * 例如想要org.springframework.data.repository.core.support.RepositoryFactorySupport的实现类，spring-data-redis中META-INF/spring.factories中正好有定义，则该实现类就会被放到该接口的实现类列表中
      	 *
      	 * Load and instantiate the factory implementations of the given type from
      	 * {@value #FACTORIES_RESOURCE_LOCATION}, using the given class loader.
      	 * <p>The returned factories are sorted through {@link AnnotationAwareOrderComparator}.
      	 * <p>If a custom instantiation strategy is required, use {@link #loadFactoryNames}
      	 * to obtain all registered factory names.
      	 * @param factoryType the interface or abstract class representing the factory
      	 * @param classLoader the ClassLoader to use for loading (can be {@code null} to use the default)
      	 * @throws IllegalArgumentException if any factory implementation class cannot
      	 * be loaded or if an error occurs while instantiating any factory
      	 * @see #loadFactoryNames
      	 */
      	public static <T> List<T> loadFactories(Class<T> factoryType, @Nullable ClassLoader classLoader) {
      		Assert.notNull(factoryType, "'factoryType' must not be null");
      		ClassLoader classLoaderToUse = classLoader;
      		if (classLoaderToUse == null) {
      			classLoaderToUse = SpringFactoriesLoader.class.getClassLoader();
      		}
      		List<String> factoryImplementationNames = loadFactoryNames(factoryType, classLoaderToUse);
      		if (logger.isTraceEnabled()) {
      			logger.trace("Loaded [" + factoryType.getName() + "] names: " + factoryImplementationNames);
      		}
      		List<T> result = new ArrayList<>(factoryImplementationNames.size());
      		for (String factoryImplementationName : factoryImplementationNames) {
      			result.add(instantiateFactory(factoryImplementationName, factoryType, classLoaderToUse));
      		}
      		AnnotationAwareOrderComparator.sort(result);
      		return result;
      	}
      
      	/**
      	 * 根据接口获取其接口类的名称，返回的是类名的列表
      	 * Load the fully qualified class names of factory implementations of the
      	 * given type from {@value #FACTORIES_RESOURCE_LOCATION}, using the given
      	 * class loader.
      	 * @param factoryType the interface or abstract class representing the factory
      	 * @param classLoader the ClassLoader to use for loading resources; can be
      	 * {@code null} to use the default
      	 * @throws IllegalArgumentException if an error occurs while loading factory names
      	 * @see #loadFactories
      	 */
      	public static List<String> loadFactoryNames(Class<?> factoryType, @Nullable ClassLoader classLoader) {
      		String factoryTypeName = factoryType.getName();
      		return loadSpringFactories(classLoader).getOrDefault(factoryTypeName, Collections.emptyList());
      	}
      
          
          /**
          * 扫描META-INF/spring.factories中所有的定义。将扫描结果放到MultiValueMap<String, String> result = cache.get(classLoader);中
          * key为接口名，value为实现类的类名
          *
          **/
      	private static Map<String, List<String>> loadSpringFactories(@Nullable ClassLoader classLoader) {
      		MultiValueMap<String, String> result = cache.get(classLoader);
      		if (result != null) {
      			return result;
      		}
      
      		try {
      			Enumeration<URL> urls = (classLoader != null ?
      					classLoader.getResources(FACTORIES_RESOURCE_LOCATION) :
      					ClassLoader.getSystemResources(FACTORIES_RESOURCE_LOCATION));
      			result = new LinkedMultiValueMap<>();
      			while (urls.hasMoreElements()) {
      				URL url = urls.nextElement();
      				UrlResource resource = new UrlResource(url);
      				Properties properties = PropertiesLoaderUtils.loadProperties(resource);
      				for (Map.Entry<?, ?> entry : properties.entrySet()) {
      					String factoryTypeName = ((String) entry.getKey()).trim();
      					for (String factoryImplementationName : StringUtils.commaDelimitedListToStringArray((String) entry.getValue())) {
      						result.add(factoryTypeName, factoryImplementationName.trim());
      					}
      				}
      			}
      			cache.put(classLoader, result);
      			return result;
      		}
      		catch (IOException ex) {
      			throw new IllegalArgumentException("Unable to load factories from location [" +
      					FACTORIES_RESOURCE_LOCATION + "]", ex);
      		}
      	}
      
          /**
          * 实例化实现类。注意反射调用的是无参的构造器
          *
          **/
      	@SuppressWarnings("unchecked")
      	private static <T> T instantiateFactory(String factoryImplementationName, Class<T> factoryType, ClassLoader classLoader) {
      		try {
      			Class<?> factoryImplementationClass = ClassUtils.forName(factoryImplementationName, classLoader);
      			if (!factoryType.isAssignableFrom(factoryImplementationClass)) {
      				throw new IllegalArgumentException(
      						"Class [" + factoryImplementationName + "] is not assignable to factory type [" + factoryType.getName() + "]");
      			}
      			return (T) ReflectionUtils.accessibleConstructor(factoryImplementationClass).newInstance();
      		}
      		catch (Throwable ex) {
      			throw new IllegalArgumentException(
      				"Unable to instantiate factory class [" + factoryImplementationName + "] for factory type [" + factoryType.getName() + "]",
      				ex);
      		}
      	}
      
      }
      ```















参考：

1. spring.factories：https://www.cnblogs.com/itplay/p/9927892.html