import {TextAreaState} from "./TextAreaState";


let buttons = {}
/**
 * 面板生成类
 */
class UIManager {

    constructor(id, panel, getString, options) {
        this.id = id;
        this.panel = panel;
        this.getString = getString;
        this.helpButton = options.helpButton;
        this.enablePreview = options.enablePreview;
    }

    run() {
        this.makeButtonRow();
        this.register();
    }

    register() {
        $(this.panel.input).on("keydown", (event) => {
            let keyCodeChar = String.fromCharCode(event.keyCode).toLowerCase();
            if ((event.ctrlKey || event.metaKey) && !event.altKey && !event.shiftKey) {
                switch (keyCodeChar) {
                    case "b":
                        this.doClick(buttons.bold);
                        break;
                    case "i":
                        this.doClick(buttons.italic);
                        break;
                    case "l":
                        this.doClick(buttons.link);
                        break;
                    case "q":
                        this.doClick(buttons.quote);
                        break;
                    case "k":
                        this.doClick(buttons.code);
                        break;
                    case "g":
                        this.doClick(buttons.image);
                        break;
                    case "o":
                        this.doClick(buttons.olist);
                        break;
                    case "u":
                        this.doClick(buttons.ulist);
                        break;
                    case "h":
                        this.doClick(buttons.heading);
                        break;
                    case "r":
                        this.doClick(buttons.hr);
                        break;
                    case "y":
                        this.doClick(buttons.redo);
                        break;
                    case "z":
                        if (event.shiftKey) {
                            this.doClick(buttons.redo);
                        }
                        else {
                            this.doClick(buttons.undo);
                        }
                        break;
                    default:
                        return;
                }
                event.preventDefault();
            }

            if (!event.shiftKey && keyCodeChar === "\t") {
                let fakeTabButton = {};
                fakeTabButton.textOp = this.bind((chunk, postProcessing) => {
                    return this.commandManager.doTab(chunk, postProcessing)
                });
                this.doClick(fakeTabButton);
                event.preventDefault();
            } else if (event.shiftKey && keyCodeChar === "\t") {
                let fakeUnTabButton = {};
                fakeUnTabButton.textOp = this.bind((chunk, postProcessing) => {
                    return this.commandManager.doTab(chunk, postProcessing, true)
                });
                this.doClick(fakeUnTabButton);
                event.preventDefault();
            }

            if ($.inArray(event.keyCode, [13]) != -1) {
                switch (event.keyCode) {
                    case 13: {
                        let fakeButton = {};
                        fakeButton.textOp = this.bind('doIndent');
                        this.doClick(fakeButton);

                        break;
                    }
                    // case 8: {
                    //     let fakeButton = {};
                    //     fakeButton.textOp = this.bind("doBackSpace");
                    //     this.doClick(fakeButton)
                    //     break;
                    // }
                }
                event.preventDefault();
            }
        }).on("keyup", (event) => {
            if (event.shiftKey && !event.ctrlKey && !event.metaKey) {
                if (event.keyCode === 13) {
                    let fakeButton = {};
                    fakeButton.textOp = this.bind('doAutoIndent');
                    this.doClick(fakeButton);
                }
            }
        })

    }

    makeButtonRow() {
        buttons.bold = this.makeButton("wmd-bold-button", this.getString("bold"), "0px", this.bind("doBold"));

        buttons.italic = this.makeButton("wmd-italic-button", this.getString("italic"), "-20px", this.bind("doItalic"));

        this.makeSpacer(1);

        buttons.link = this.makeButton("wmd-link-button", this.getString("link"), "-40px", this.bind((chunk, postProcessing) => {
            return this.commandManager.doLinkOrImage(chunk, postProcessing, false);
        }));

        buttons.quote = this.makeButton("wmd-quote-button", this.getString("quote"), "-60px", this.bind("doBlockQuote"));

        buttons.code = this.makeButton("wmd-code-button", this.getString("code"), "-80px", this.bind("doCode"));

        buttons.image = this.makeButton("wmd-image-button", this.getString("image"), "-100px", this.bind((chunk, postProcessing) => {
            return this.commandManager.doLinkOrImage(chunk, postProcessing, true);
        }));

        this.makeSpacer(2);

        buttons.olist = this.makeButton("wmd-olist-button", this.getString("olist"), "-120px", this.bind((chunk, postProcessing) => {
            return this.commandManager.doList(chunk, postProcessing, true);
        }));

        buttons.ulist = this.makeButton("wmd-ulist-button", this.getString("ulist"), "-140px", this.bind((chunk, postProcessing) => {
            return this.commandManager.doList(chunk, postProcessing, false);
        }));

        buttons.heading = this.makeButton("wmd-heading-button", this.getString("heading"), "-160px", this.bind("doHeading"));

        buttons.hr = this.makeButton("wmd-hr-button", this.getString("hr"), "-180px", this.bind("doHorizontalRule"));

        this.makeSpacer(3);


        // 撤销和恢复,需要借助UndoManager
        buttons.undo = this.makeButton("wmd-undo-button", this.getString("undo"), "-200px", null);
        buttons.undo.execute = function (manager) {
            if (manager) manager.undo();
        };

        let redoTitle = /win/.test(window.navigator.platform.toLowerCase()) ? this.getString("redo") : this.getString("redomac");
        buttons.redo = this.makeButton("wmd-redo-button", redoTitle, "-220px", null);
        buttons.redo.execute = function (manager) {
            if (manager) manager.redo();
        };

        this.makeSpacer(4);

        buttons.help = this.makeButton("wmd-help-button", this.getString("help"), "-300px", this.helpButton)

        if (this.enablePreview) {
            buttons.viewMode = this.makeButton('wmd-view-button', '', '-360px', null, 'right');
            buttons.viewMode.execute = () => {
                this.panel.setMode("viewMode");
                this.setEditorStates();
            };

            buttons.liveMode = this.makeButton('wmd-live-button', '', '-340px', null, 'right');
            buttons.liveMode.execute = () => {
                this.panel.setMode("liveMode");
                this.setEditorStates();
            };

            buttons.editMode = this.makeButton('wmd-edit-button', '', '-320px', null, 'right');
            buttons.editMode.execute = () => {
                this.panel.setMode("editMode");
                this.setEditorStates();
            };

            this.makeSpacer(5);

            this.setEditorStates();
        }

        buttons.fullModel = this.makeButton('wmd-full-button', '', '-240px', null, 'right');
        buttons.fullModel.execute = () => {
            this.panel.makeFull(true);
            this.setPanelStates();
        };

        buttons.normal = this.makeButton('wmd-normal-button', '', '-260px', null, 'right');
        buttons.normal.execute = () => {
            this.panel.makeFull(false);
            this.setPanelStates();
        };
        // 重新设置撤销和恢复按钮的状态
        this.setUndoRedoButtonStates();
        this.setPanelStates();
    }

    /**
     * 重新设置撤销和恢复按钮的状态
     */
    setUndoRedoButtonStates() {
        if (this.undoManager) {
            this.setupButton(buttons.undo, this.undoManager.canUndo());
            this.setupButton(buttons.redo, this.undoManager.canRedo());
        }
    }

    setEditorStates() {
        this.setupButton(buttons.viewMode, this.panel.mode != 'viewMode');
        this.setupButton(buttons.editMode, this.panel.mode != 'editMode');
        this.setupButton(buttons.liveMode, this.panel.mode != 'liveMode');
    }

    setPanelStates() {
        if (this.panel.isFull) {
            buttons.fullModel.hide();
            buttons.normal.show();
        } else {
            buttons.normal.hide();
            buttons.fullModel.show();
        }
    }

    /**
     * 生成按钮
     * @param id
     * @param title
     * @param XShift
     * @param xPosition
     * @param textOp
     * @returns {*|jQuery}
     */
    makeButton(id, title, XShift, textOp, float = "left") {

        let button = $("<li></li>").addClass("wmd-button").attr("id", id + "_" + this.id);
        float == 'right' ? button.css({float: float}) : null;
        let buttonImage = $("<a href='javascript:;'></a>");

        button.append(buttonImage);
        button.attr("title", title);
        button.XShift = XShift;

        if (textOp) {
            button.textOp = textOp;
        }

        this.setupButton(button, true);
        $(this.panel.toolbar).append(button);

        return button;
    }

    /**
     * 生成分隔符
     * @param num
     */
    makeSpacer(num) {
        let spacer = $("<li></li>").addClass("wmd-button-spacer wmd-button-spacer" + num).attr("id", "wmd-spacer" + num + "_" + this.id);
        $(this.panel.toolbar).append(spacer);
    }

    /**
     * 按钮滤镜
     * @param button
     * @param isEnabled
     */
    setupButton(button, isEnabled) {
        let buttonImage = button.find("a"), normalYShift = "0px", disabledYShift = "-20px", highlightYShift = "-40px";
        buttonImage.css("background-position", button.XShift + " " + normalYShift);

        if (isEnabled) {
            if (button.enable === undefined) {
                button.on('click', (event) => {
                    if (!button.enable) { return false; }
                    this.doClick(button);
                }).on('mouseover', (event) => {
                    if (!button.enable) { return false; }
                    buttonImage.css("background-position", button.XShift + " " + highlightYShift);
                }).on('mouseout', (event) => {
                    if (!button.enable) { return false; }
                    buttonImage.css("background-position", button.XShift + " " + normalYShift);
                });
            }
            button.enable = true;
        }
        else {
            buttonImage.css("background-position", button.XShift + " " + disabledYShift);
            button.enable = false;
        }
    }

    /**
     * 绑定命令
     * @param method
     * @returns {Function}
     */
    bind(method) {
        if (typeof method === "string")
            method = this.commandManager[method];
        let _that = this;
        return function () {
            method.apply(_that.commandManager, arguments);
        };
    }

    doClick(button) {
        this.panel.input.focus();

        if (button.textOp) {

            if (this.undoManager) {
                this.undoManager.setCommandMode();
            }

            let state = new TextAreaState(this.panel.input);

            if (!state) {
                return;
            }

            let chunk = state.getChunk();

            let fixupInputArea = () => {

                this.panel.input.focus();

                if (chunk) {
                    state.setChunk(chunk);
                }

                state.restore();
                if (this.previewManager)
                    this.previewManager.refresh();
            };

            let noCleanup = button.textOp(chunk, fixupInputArea);

            if (!noCleanup) {
                fixupInputArea();
            }

        }

        if (button.execute) {
            button.execute(this.undoManager);
        }
    }
}

export {UIManager};