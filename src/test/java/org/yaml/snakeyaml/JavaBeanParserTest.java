package org.yaml.snakeyaml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;

import junit.framework.TestCase;

public class JavaBeanParserTest extends TestCase {

    @SuppressWarnings("deprecation")
    public void testLoadString() {
        Bean bean = new Bean();
        bean.setId(3);
        bean.setName("Test me.");
        Yaml yaml = new Yaml();
        String output = yaml.dump(bean);
        assertEquals("!!org.yaml.snakeyaml.JavaBeanParserTest$Bean {id: 3, name: Test me.}\n",
                output);
        Bean parsed = JavaBeanParser.load(output, Bean.class);
        assertEquals(3, parsed.getId());
        assertEquals("Test me.", parsed.getName());
        // Runtime definition is more important
        Bean2 parsed2 = JavaBeanParser.load(output, Bean2.class);
        assertEquals(3, parsed2.getId());
        assertEquals("Test me.", parsed2.getName());
        assertFalse(parsed2.isValid());
    }

    @SuppressWarnings("deprecation")
    public void testLoadInputStream() {
        String yaml = "!!org.yaml.snakeyaml.JavaBeanParserTest$Bean {id: 3, name: Test me.}\n";
        InputStream input = new ByteArrayInputStream(yaml.getBytes());
        Bean parsed = JavaBeanParser.load(input, Bean.class);
        assertEquals(3, parsed.getId());
        assertEquals("Test me.", parsed.getName());
    }

    @SuppressWarnings("deprecation")
    public void testLoadReader() {
        String yaml = "!!org.yaml.snakeyaml.JavaBeanParserTest$Bean {id: 3, name: Test me.}\n";
        java.io.Reader input = new StringReader(yaml);
        Bean parsed = JavaBeanParser.load(input, Bean.class);
        assertEquals(3, parsed.getId());
        assertEquals("Test me.", parsed.getName());
    }

    public static class Bean {
        private String name;
        private int id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class Bean2 {
        private String name;
        private int id;
        private boolean valid;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isValid() {
            return valid;
        }

        public void setValid(boolean valid) {
            this.valid = valid;
        }
    }
}
