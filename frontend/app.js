const API = "http://localhost:8080/api/items";
const $ = (id) => document.getElementById(id);

async function save() {
    const text = $("text").value.trim();
    if (!text) return alert("Text missing");
    await fetch(API, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ text })
    });
    $("text").value = "";
    await loadAll();
}

async function loadAll() {
    const res = await fetch(API);
    const data = await res.json();
    const list = $("list");
    list.innerHTML = "";
    data.forEach(i => {
        const li = document.createElement("li");
        const ts = i.createdAt ? new Date(i.createdAt).toLocaleString() : "";
        li.textContent = `${i.text} ${ts && "— " + ts}`;
        list.appendChild(li);
    });
}

$("save").onclick = save;
$("load").onclick = loadAll;