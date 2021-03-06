package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.*;

import java.util.Arrays;


@RestController
@CrossOrigin
@RequestMapping("/{text}/")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get(@PathVariable String text,
                              @RequestParam(value="transforms", defaultValue="upper") String[] transforms) {

        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));
        System.out.println(text);

        // do the transformation, you should run your logic here, below just a silly example
        TextTransformer transformer = buildTransformer(transforms);
        return transformer.transform(text);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(@PathVariable String text,
                      @RequestBody String[] transforms) {
        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));

        // do the transformation, you should run your logic here, below just a silly example
        TextTransformer transformer = buildTransformer(transforms);
        return transformer.transform(text);
    }

    private TextTransformer buildTransformer(String[] transforms){
        TextTransformer transformer = new SimpleText();
        for (int i = 0; i < transforms.length; i++){
            switch (transforms[i]){
                case "upper": transformer = new UpperCase(transformer);
                    break;
                case "lower": transformer = new LowerCase(transformer);
                    break;
                case "capitalize": transformer = new Capitalize(transformer);
                    break;
                case "inverse": transformer = new Inverse(transformer);
                    break;
                case "nr_to_words": transformer = new NumberToWords(transformer, null);
                    break;
                case "removeDuplicatedWords": transformer = new RemoveDuplicates(transformer);
                    break;
                case "toShortcuts": transformer = new Shorten(transformer);
                    break;
                case "extendShortcuts": transformer = new ExtendShortcut(transformer);
                    break;
                case "toLatex": transformer = new Latex(transformer);
                    break;
                case "formatBlankSigns": transformer = new BlankSignsFormater(transformer);
                    break;
                case "timeToWords": transformer = new HourTransformer(transformer);
                    break;
                case "translate": transformer = new Translator(transformer,transforms[i+1]);
            }
        }
        return transformer;
    }

}


