import {UIManager} from "./UIManager";
import {Panel} from "./Panel";
import {Dialog} from "./Dialog";
import {CommandManager} from "./CommandManager";
import {UndoManager} from "./UndoManager";
import {Preview} from "./Preview";
import {Format} from "./Format";
import {PreviewManager} from "./PreviewManager";
let HyperDown = require("hyperdown");
/**
 * 编辑器类
 */
class MdEditor {

    constructor(id, opts) {
        this.id = id;
        this.events = new Map();
        this.options = $.extend(options, opts);

    }

    getString(key) {
        return options.strings[key] || defaultsStrings[key];
    }

    run() {
        let panel, dialog, commandManager, undoManager, previewManager;

        panel = new Panel(this.id, this.options);
        dialog = new Dialog(this.options);

        commandManager = new CommandManager(this.getString);
        commandManager.dialog = dialog;
        undoManager = new UndoManager(panel.input);

        if (this.options.enablePreview) {
            let format = new Format(panel.input, this.options);
            let preview = new Preview(panel.preview, this.options);
            previewManager = new PreviewManager(panel, format, preview, this.options);
        }

        this.uiManager = new UIManager(this.id, panel, this.getString, this.options);

        undoManager.callback = () => {
            this.uiManager.setUndoRedoButtonStates();
            if (options.enablePreview)
                previewManager.refresh();
        };

        this.uiManager.commandManager = commandManager;
        this.uiManager.undoManager = undoManager;
        this.uiManager.previewManager = previewManager;

        this.uiManager.run();
    }
}

let options = {
      converter: (new HyperDown())
    , placeholder: `欢迎使用md.editor编辑器\n
希望你有一个快乐的使用体验`
    , enablePreview: true
    , height: "400px"
    , uploadUrl: ""
    , highlight: false
    , flowchart: false
    , helpButton: function () {
        alert("欢迎使用md.editor编辑器!!!");
    }
};

let defaultsStrings = {
      bold: "粗体 <strong> Ctrl+B"
    , boldexample: "加粗文本"

    , italic: "斜体 <em> Ctrl+I"
    , italicexample: "斜体文本"
    , link: "链接 <a> Ctrl+L"
    , linkdescription: "链接描述"
    , linkdialog: "插入链接"

    , quote: "引用 <blockquote> Ctrl+Q"
    , quoteexample: "引用文本"

    , code: "代码 <pre><code> Ctrl+K"
    , codeexample: "请输入代码"

    , image: "图片 <img> Ctrl+G"
    , imagedescription: "输入图片地址"
    , imagedialog: "插入图片"

    , olist: "数字列表 <ol> Ctrl+O"
    , ulist: "普通列表 <ul> Ctrl+U"
    , litem: "列表项目"

    , heading: "标题 <h1>/<h2> Ctrl+H"
    , headingexample: "标题文本"

    , hr: "分割线 <hr> Ctrl+R"

    , undo: "撤销 - Ctrl+Z"
    , redo: "重做 - Ctrl+Y"
    , redomac: "重做 - Ctrl+Shift+Z"

    , help: "Markdown 帮助"
};

export {MdEditor};




